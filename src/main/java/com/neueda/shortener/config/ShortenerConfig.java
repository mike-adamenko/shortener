package com.neueda.shortener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Shortener configuration
 *
 * @author Mihail Adamenko
 */
@Configuration
@EnableSwagger2
public class ShortenerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neueda.shortener"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "URL Shortener REST API",
                "URL Shortener app",
                "API v.0.0.1",
                null,
                new Contact("Mike Adamenko", "https://github.com/mike-adamenko/shortener", "mnadamenko@gmail.com"),
                null, null, Collections.emptyList());
    }
}