package com.kv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.kv")
@ServletComponentScan
@PropertySource("file:application.properties")
public class DynamicIpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicIpApplication.class, args);
	}
}
