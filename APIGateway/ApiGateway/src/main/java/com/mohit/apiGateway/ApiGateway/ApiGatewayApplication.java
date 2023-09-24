package com.mohit.apiGateway.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages={"com.mohit.apiGateway.ApiGateway.globalFilter","com.mohit.apiGateway.ApiGateway","com.eaztbytes.gatewayserver.filters","filters"})
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
