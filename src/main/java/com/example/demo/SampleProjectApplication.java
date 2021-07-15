package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import Bean.ComponentTest;


@SpringBootApplication
@ComponentScan(basePackages = {"Bean"})
@ComponentScan(basePackageClasses = Controller.ControllerTest.class)
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
