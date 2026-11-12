package com.example.department_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI departmentOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Department Service Team");
        contact.setEmail("support@example.com");

        Info info = new Info()
                .title("Department Management API")
                .version("1.0.0")
                .description(
                    "REST API for managing departments. " +
                    "Supports CRUD operations, validation, " +
                    "searching, pagination and sorting."
                )
                .contact(contact);

        Server server = new Server();
        server.setUrl("http://localhost:8082");
        server.setDescription("Local Development Server");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}