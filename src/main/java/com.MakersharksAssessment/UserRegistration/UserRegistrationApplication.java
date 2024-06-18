package com.MakersharksAssesment.UserRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class UserRegistrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}

	@Bean
	public Docket configurationSwagger(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/user/*"))
				.apis(RequestHandlerSelectors.basePackage("com.MakersharksAssesment.UserRegistration"))
				.build()
				.apiInfo(apiInfoCustomData());
	}

	private ApiInfo apiInfoCustomData(){
		return new ApiInfo(
				"User Registration API Application",
				"User Registration Application Documentation",
				"1.0",
				"User Registration Service Terms",
				new Contact("Vaishnavi Saxena", "",
						"vaishnavi.saxena020@gmail.com"),
				"Licence 1.0",
				"",
				Collections.emptyList()
		);
	}

}
