package com.example.newsFeedApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "NewsFeedApp",
		description = "Веб-приложение по управлению новостной лентой на сайте"
))
public class NewsFeedAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFeedAppApplication.class, args);
	}

}
