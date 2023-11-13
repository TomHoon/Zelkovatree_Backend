package com.zelkovatree.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsController implements WebMvcConfigurer {

	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	        .allowedOrigins("*");
//	    	.allowedOrigins("http://49.247.40.173");
	  }
}
