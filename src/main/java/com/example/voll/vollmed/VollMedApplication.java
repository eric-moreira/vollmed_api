package com.example.voll.vollmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class VollMedApplication {

	public static void main(String[] args) {
		SpringApplication.run(VollMedApplication.class, args);
	}

}
