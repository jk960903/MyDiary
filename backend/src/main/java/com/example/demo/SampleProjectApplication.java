package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
@EnableJpaRepositories(basePackages= {"com.example.demo"})
public class SampleProjectApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(SampleProjectApplication.class, args);
	
	}
	

}
