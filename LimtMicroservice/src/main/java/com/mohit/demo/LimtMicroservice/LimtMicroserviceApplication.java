package com.mohit.demo.LimtMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import feign.RequestInterceptor;
import io.micrometer.tracing.Tracer;
@SpringBootApplication
@EnableFeignClients("com.currency.controller")
@EnableJpaRepositories("com.currency.dao")
@EntityScan("com.currency.lib")
@ComponentScan(basePackages={"com.currency.controller","com.currency.dao","com.currency.lib"})
public class LimtMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimtMicroserviceApplication.class, args);
	}
	

	    @Bean
	    public RequestInterceptor braveRequestInterceptor(Tracer tracer) {
	        return template -> {
	                template.header("x-b3-traceid" , tracer.currentSpan().context().traceId());
	                template.header("x-b3-spanid", tracer.currentSpan().context().spanId());
	        };
	    }

}
