package com.ssafy.artmate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.artmate.interceptor.JwtInterceptor;

@SpringBootApplication
public class BackendApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Autowired
    private JwtInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) { // JWTInterceptor를 설치한다.
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**") // 기본 적용 경로
                .excludePathPatterns(Arrays.asList("/user/login/**"));// 적용 제외 경로
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) { // Interceptor를 이용해서 처리하므로 전역의 Corss Origin 처리를 해준다.
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("auth-token");
    }
}
