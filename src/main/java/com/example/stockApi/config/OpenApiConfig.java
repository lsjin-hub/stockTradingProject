package com.example.stockApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {

	// API 그룹화 (선택 사항)
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.OAS_30)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
	    }

	 public ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("SpringBoot Practice Rest API Documentation")
	                .description("springboot rest api practice.")
	                .version("0.1")
	                .build();
	    } 

}
