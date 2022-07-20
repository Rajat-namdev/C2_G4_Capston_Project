package com.shopForHome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Welcome")
public class WelcomePageController {

	@GetMapping("/hello")
	public String welcomePage() {
		
		return "Welcome to SpringBoot:)";
		
	}
}
