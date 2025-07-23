package com.bieliaiev.tarkov_pmc_tools.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bieliaiev.tarkov_pmc_tools.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ItemService service;
	
	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/items")
	public String showItems(@RequestParam(required = false) String category, Model model) {
		
		if(category == null) {
			
			try {
				model.addAttribute("items", service.getItems());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			return "items";
			
		} else {
			
			try {
				model.addAttribute("items", service.getItemsByCategory(category));
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			
			return "items";
		}
	}
}
