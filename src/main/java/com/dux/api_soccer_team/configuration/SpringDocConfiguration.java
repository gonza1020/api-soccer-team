package com.dux.api_soccer_team.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Swagger SoccerApi")
                                .description("This is a simple soccer teams api, its provides information about teams. ")
                                .contact(
                                        new Contact()
                                                .email("gonzalozoloaga44@gmail.com")
                                )
                                .version("1.0.0")
                )
        ;
    }
}