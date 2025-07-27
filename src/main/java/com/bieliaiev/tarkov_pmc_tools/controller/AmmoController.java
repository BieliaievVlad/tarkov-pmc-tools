package com.bieliaiev.tarkov_pmc_tools.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bieliaiev.tarkov_pmc_tools.dto.ammo.AmmoDto;
import com.bieliaiev.tarkov_pmc_tools.service.ammo.AmmoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AmmoController {

	private final AmmoService service;
	
	@GetMapping("/allAmmo")
	public String showAllAmmoPage(
			@RequestParam (required = false) String sort,
			@RequestParam (required = false, defaultValue = "asc") String order,
			Model model) {
		
		try {
			List<AmmoDto> allAmmo = service.sortAmmoList(sort, order);
			model.addAttribute("allAmmo", allAmmo);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		model.addAttribute("sort", sort);
		model.addAttribute("order", order);

		return "allAmmo";
	}
	
	@GetMapping("/ammo")
	public String showAmmoPage(@RequestParam String normalizedName, Model model) {
		
		try {
			model.addAttribute("ammo", service.getAmmoByNormalizedName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return "ammo";
	}
}
