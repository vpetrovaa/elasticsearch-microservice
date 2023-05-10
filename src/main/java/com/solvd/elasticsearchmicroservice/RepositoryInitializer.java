package com.solvd.elasticsearchmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RepositoryInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RepositoryInitializer.class, args);
	}

}
