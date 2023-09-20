package com.samat.money.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicUserApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .packagesToScan("com.samat.money.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Money Application API")
                        .version("1.0")
                        .description("API for application Many")
                        .contact(new Contact()
                                .name("Salavat Seitbek uulu")
                                .email("seitbekuulusalavat@gmail.com")
                                .url("https://www.linkedin.com/in/salavat-seitbek-uulu-02178a1b7")
                        )
                )
                .servers(List.of(
                                new Server()
                                        .url("http://localhost:8080")
                                        .description("Dev service")
                        )
                );
    }
}
