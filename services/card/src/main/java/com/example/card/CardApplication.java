package com.example.card;

import com.example.card.dto.CardContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Bank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Pham Hiep",
						email = "phamtrunghiep1201@gmail.com",
						url = "https://github.com/PhamHiep1"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/PhamHiep1"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank Cards microservice REST API Documentation",
				url = "https://github.com/PhamHiep1/microservice-bank-app"
		)
)
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}
}