package com.fooddeliveryapp.storefront;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StorefrontApplication {

    @Bean
    public RestTemplate getRestTemplate(){return new RestTemplate();}

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }
    @Bean
    ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("Food Delivery Spring Boot API").version("1.0").license("(C) Copyright Food Delivery")
                .description("List of all endpoints used in API");
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(StorefrontApplication.class, args);
    }

}
