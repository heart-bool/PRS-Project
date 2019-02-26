package com.prs.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ConditionalOnProperty(prefix = "prs.swagger", value = {"enable"}, havingValue = "true")
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
	public static final String API_URI = "/v2/api-docs";
	private final RouteDefinitionLocator routeLocator;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();

		// 配置 swagger 从注册中心的服务列表中获取服务接口文档，
		// 这样在 gateway 的配置文件中将不再需要手动的配置服务路由，
		// 当普通服务注册到eureka的时候，将自动的读取服务的接口文档
		routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
			Stream<PredicateDefinition> predicateDefinitionStream = routeDefinition.getPredicates().stream().filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()));
			Map<String, String> args = predicateDefinitionStream.findFirst().get().getArgs();
			String pattern = args.get("pattern");
			String path = args.get(NameUtils.GENERATED_NAME_PREFIX + "0");

			if (pattern != null && !pattern.contains("server")) {
				resources.add(swaggerResource(routeDefinition.getId(),
						pattern.replace("/**", API_URI)));
			}

			if (path != null) {
				resources.add(swaggerResource(routeDefinition.getId(),
						path.replace("/**", API_URI)));
			}
		});
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setUrl(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}
}