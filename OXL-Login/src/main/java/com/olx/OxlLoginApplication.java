package com.olx;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class OxlLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxlLoginApplication.class, args);
	}
	@Bean
	public Docket getCustomizedDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
				//.paths(PathSelectors.ant("/olx/login/*"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	public ApiInfo getApiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"OLX Login RestAPI Documentation",
				"This page gives REST API documentation for OLX Login",
				"2.5",
				"My Terms of Service",
				new Contact("SaiRam", "http://sairam.com" , "sairam.sura@zensar.com"),
				"GPL",
				"http://gpl.org",
				new ArrayList<VendorExtension>()
				);
		return apiInfo;
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
