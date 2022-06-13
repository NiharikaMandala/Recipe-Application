package com.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/*@SpringBootApplication : This annotation is used in our Application or Main class to enable a 
host of features, e.g. Java-based Spring configuration, component scanning, and in particular 
 for enabling Spring Boot's auto-configuration feature.*/

@SpringBootApplication

public class RecipeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeProjectApplication.class, args);
	}

}
