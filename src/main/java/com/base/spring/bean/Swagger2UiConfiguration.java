package com.base.spring.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.base.base.Constants;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@EnableSwagger2
public class Swagger2UiConfiguration  extends WebMvcConfigurerAdapter{
	@Bean
	public Docket api() {
		// @formatter:off
        //Register the controllers to swagger
        //Also it is configuring the Swagger Docket
		
		System.out.println("Swagger2UiConfiguration Swagger2UiConfiguration");
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
                 .apis(RequestHandlerSelectors.any())
                 .paths(PathSelectors.any())
                 .build()
                 .globalOperationParameters(headers());  
	}

	List<Parameter> headers(){
		ArrayList<Parameter> headers = new ArrayList<>();
		headers.add(addHeader(Constants.HEADER_AUTH_SESSION_ID, "Session Id"));
		headers.add(addHeader(Constants.HEADER_AUTH_SESSION_TOKEN, "Session Token"));
		headers.add(addHeader(Constants.HEADER_DEVICE_APP_VERSION, "App Version"));
		headers.add(addHeader(Constants.HEADER_DEVICE_BRAND, "Device Brand"));
		headers.add(addHeader(Constants.HEADER_DEVICE_ID, "Device Id Assigned By server"));
		headers.add(addHeader(Constants.HEADER_DEVICE_MODEL, "Device Model"));
		headers.add(addHeader(Constants.HEADER_DEVICE_OPERATING_NAME, "Device Operating System Name"));
		headers.add(addHeader(Constants.HEADER_DEVICE_OPERATING_SYSTEM, "Device Operating System : 1 android, 2 ios"));
		headers.add(addHeader(Constants.HEADER_DEVICE_OS_VERSION, "Operating system Version"));
		headers.add(addHeader(Constants.HEADER_DEVICE_UID, "Unique Identifier for device"));
		return headers;
	}
	
	Parameter addHeader(String name,String description) {
		return new ParameterBuilder().name(name).modelRef(new ModelRef("string")).required(true).parameterType("header").description(description).build();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/");
	    registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}