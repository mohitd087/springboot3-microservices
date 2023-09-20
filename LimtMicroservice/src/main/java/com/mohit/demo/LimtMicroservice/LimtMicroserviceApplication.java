package com.mohit.demo.LimtMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.currency.dao")
@EntityScan("com.currency.lib")
@ComponentScan(basePackages={"com.currency.controller","com.currency.dao","com.currency.lib"})
public class LimtMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimtMicroserviceApplication.class, args);
	}

}
