package com.example.car_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@OpenAPIDefinition(
    info = @Info(
        title = "Car Service Management System API",
        version = "1.0",
        description = "REST APIs for managing admins, memberships, customers, vehicles, mechanics, services, bookings, and payments."
    )
)

@SpringBootApplication
public class CarManagementApplication
        extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(CarManagementApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CarManagementApplication.class, args);
    }
}