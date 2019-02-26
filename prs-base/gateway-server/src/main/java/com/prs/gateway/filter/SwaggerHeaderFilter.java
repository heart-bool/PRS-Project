package com.prs.gateway.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


/***
 * spring cloud 2.1 中不需要添加 X-Forwarded-Prefix
 * 若添加该头 会导致通过网关swagger执行请求时将X-Forwarded-Prefix拼接到已有的url上
 * 导致url错误而404
 *
 */
@ConditionalOnProperty(prefix = "prs.swagger", value = {"enable"}, havingValue = "true")
@Component
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
//            if (StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI)) {
//                return chain.filter(exchange);
//            }

//            String basePath = path.substring(0, path.lastIndexOf(SwaggerProvider.API_URI));
//
//            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
//            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(exchange);
        };
    }
}