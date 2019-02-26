package com.prs.gateway.filter;

import com.prs.cache.Constant;
import com.prs.cache.tools.HashRedisTools;
import com.prs.domain.response.TokenResponseEntity;
import com.prs.gateway.properties.PrsGatewayProperties;
import com.prs.gateway.config.SwaggerProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Configuration
@Log4j2
public class RequestFilter implements GlobalFilter {

	private static final String TOKEN = "token";
	private static final String SWAGGER_RESOURCE = "swagger-ui.html";
	private static final String UNAUTHORIZED = "{\"code\":\"401\",\"message\":\"UNAUTHORIZED\"}";
	private static final String TOKEN_EXPIRE = "{\"code\":\"601\",\"message\":\"token expire\"}";

	@Autowired
	private HashRedisTools hashRedisTools;

	@Autowired
	private PrsGatewayProperties prsGatewayProperties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();
		if (path.contains(SwaggerProvider.API_URI)
				|| prsGatewayProperties.getNoTokenApis().contains(path)) {
			return chain.filter(exchange);
		}

		String token = request.getHeaders().getFirst(TOKEN);
		if (token == null || token.isEmpty()) {
			return unauthorized(exchange, UNAUTHORIZED);
		}

		TokenResponseEntity tokenRedisEntity = (TokenResponseEntity) hashRedisTools.get(Constant.AUTH_HASH, token);
		long time = new Date().getTime();
		// 不存在
		if (tokenRedisEntity == null) {
			return unauthorized(exchange, UNAUTHORIZED);
		}

		// 过期
		if (tokenRedisEntity.getExpire() < time) {
			return unauthorized(exchange, TOKEN_EXPIRE);
		}

		return chain.filter(exchange);
	}

	private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
		return exchange.getResponse().writeWith(Flux.just(buffer));
	}
}
