package com.example.users;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.example.common.entities",
		"com.example.users.entity"
})
@OpenAPIDefinition(info = @Info(title = "User Service API", version = "1.0", description = "User-Service Microservice"))
public class UserManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementSystemApplication.class, args);
	}

}
