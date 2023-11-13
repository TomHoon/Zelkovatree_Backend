package com.zelkovatree.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController implements ErrorController{
	
	@GetMapping("/error")
	public String redirectRoot() {
		System.out.println("에러핸들링 redirect 시작.. >>> ");
		return "index.html";
	}
	
	public String getErrorPath() {
		System.out.println("에러핸들링 getErrorPath 시작.. >>> ");
		return null;
	}
	

}