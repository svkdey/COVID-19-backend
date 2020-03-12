package com.example.Covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
//adding swagger dependency
@EnableSwagger2
public class Covid19Application {
	//adding swagger basic config
	 @Bean
	 public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .paths(PathSelectors.ant("/*"))
	          .apis(RequestHandlerSelectors.basePackage("com.example.Covid19"))
	          .build()
	          .apiInfo(getApiInfo());
	    }
	 private ApiInfo getApiInfo() {
		 return new ApiInfo("COVID-19","simple dashboard app","1.0.0","https://svkdey.github.io/resume-app/", "Souvik dey","","");
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(Covid19Application.class, args);
	}

}
