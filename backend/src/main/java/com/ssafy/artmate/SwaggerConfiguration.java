package com.ssafy.artmate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAsync
@EnableWebMvc
@EnableSwagger2

public class SwaggerConfiguration implements WebMvcConfigurer {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ARTMATE").description("아트메이스 Api 문서").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("artmate").apiInfo(this.apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.artmate.controller"))
				.paths(PathSelectors.ant("/api/**")).build();
	}

}
