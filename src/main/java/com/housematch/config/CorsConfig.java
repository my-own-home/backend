package com.housematch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://127.0.0.1:5500", "http://127.0.0.1:9000", "http://localhost:3000", "http://localhost:9000") // 라이브서버 주소
        .allowedMethods("*")
		.allowCredentials(true);
	}
	
}

