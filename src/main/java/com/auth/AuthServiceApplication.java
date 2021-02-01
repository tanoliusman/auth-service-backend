package com.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        log.info("************** "+url);
        log.info("************** "+username);
        log.info("************** "+password);
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL);
    }
}

