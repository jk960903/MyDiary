package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Bean.ComponentTest;
import com.example.demo.Repository.*;


@SpringBootApplication
@ComponentScan(basePackages = {"Bean"})
@ComponentScan(basePackages = {"Controller"})
@EnableJpaRepositories(basePackages= {"com.example.demo.Repository"})
public class SampleProjectApplication implements CommandLineRunner{
	@Autowired
	ComponentTest component;
	
	public static void main(String[] args) {
		SpringApplication.run(SampleProjectApplication.class, args);
	
	}
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		component.run();
	}
}
