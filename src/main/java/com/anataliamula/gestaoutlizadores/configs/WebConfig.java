package com.anataliamula.gestaoutlizadores.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");//Libera qualquer requisição a partir do /, a partir do 8080 tudo é aceito
	}
}
