package com.mohit.apiGateway.ApiGateway.globalFilter;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalFilter implements GlobalFilter {
	private Logger logger= (Logger) LoggerFactory.getLogger(LoggingGlobalFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Path of request received -> {}", exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
