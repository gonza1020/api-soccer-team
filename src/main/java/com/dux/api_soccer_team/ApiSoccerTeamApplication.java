package com.dux.api_soccer_team;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"com.dux.api_soccer_team", "com.dux.api_soccer_team.controller" , "com.dux.api_soccer_team.configuration"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class ApiSoccerTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSoccerTeamApplication.class, args);
    }

    @Bean(name = "org.openapitools.ApiSoccerTeamApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}