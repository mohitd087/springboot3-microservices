package com.eaztbytes.gatewayserver.filters;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import ch.qos.logback.classic.Logger;
import filters.FilterUtility;
import reactor.core.publisher.Mono;

@Component
public class PostFilter implements GlobalFilter {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(PostFilter.class);
    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            String correlationId = filterUtility.getCorrelationId(requestHeaders);
            // add the correlation id to the outbound headers.
            logger.info("Updating the correlation-id in Response header {}", correlationId);
            exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID, correlationId);
        }));
    }
}
