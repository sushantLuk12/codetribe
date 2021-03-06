package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages ="dao")
@ComponentScan(basePackages = {"controller","service","dao","model","config"})
@EntityScan( basePackages = {"entities"} )
public class LukApplication {

	public static void main(String[] args) {
		SpringApplication.run(LukApplication.class, args);
	}

}
