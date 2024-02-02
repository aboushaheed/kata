package com.tennis.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
public class TennisMatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisMatchServiceApplication.class, args);
	}

}
