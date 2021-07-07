package Controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Bean.ComponentTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
@SpringBootApplication
public class ControllerTest {
	
	@Autowired
	ComponentTest test;
	
}
