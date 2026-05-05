package com.belleza.inventario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventario Belleza API")
                        .version("1.0")
                        .description("API REST para gestionar el inventario de productos de belleza")
                        .contact(new Contact()
                                .name("Sebastian Rios")
                                .email("tu-email@example.com")));
    }
}