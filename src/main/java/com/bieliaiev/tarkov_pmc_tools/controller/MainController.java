package com.bieliaiev.tarkov_pmc_tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}
}
