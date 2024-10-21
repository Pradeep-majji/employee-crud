package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan
@EnableWebMvc
public class DemoApplication {

	public static void main(String[] args) {
		//System.out.println("testing");
		SpringApplication.run(DemoApplication.class, args);
		//System.out.println("app started");
	}

}
