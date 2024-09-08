package com.example.journals;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.example.common.entities",
		"com.example.journals"
})
@OpenAPIDefinition(info = @Info(title = "User Journal Service API", version = "1.0", description = "user journal microservice"))
public class UserJournalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserJournalServiceApplication.class, args);
	}

}
