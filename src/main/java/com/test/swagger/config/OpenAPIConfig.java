package com.test.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Management using Swagger")
                        .description("API documentation created using Springdoc OpenAPI and Swagger UI")
                        .version("1.0.0"));
    }
}