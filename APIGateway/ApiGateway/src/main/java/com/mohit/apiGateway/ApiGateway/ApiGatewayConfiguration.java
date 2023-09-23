package com.mohit.apiGateway.ApiGateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
				.filters(f -> f.addRequestHeader("MyHeader", "MyUrl").addRequestParameter("Param", "MyValue"))
				.uri("http://httpbin.org:80");
		return builder.routes().route(routeFunction).route(p -> p.path("/limitService/**").uri("lb://LIMIT-SERVICE"))
				.route(p -> p.path("/limit-new/**")
						//Now this link is also working - http://localhost:8765/limit-new/INR/currency/USD/100
						.filters(f -> f.rewritePath("/limit-new/(?<segment>.*)", "/limitService/${segment}"))
						.uri("lb://LIMIT-SERVICE"))
				.build();
	}

}