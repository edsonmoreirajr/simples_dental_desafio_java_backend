package com.simplesdental.desafiojavabackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI simplesDentalOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Simples Dental Api")
                        .description("Aplicação para testar os conhecimentos técnicos na prática")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Simples Dental desafio Java backend")
                        .url("https://github.com/edsonmoreirajr/simples_dental_desafio_java_backend/wiki"));
    }
}