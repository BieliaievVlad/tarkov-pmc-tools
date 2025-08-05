package com.bieliaiev.tarkov_pmc_tools.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponViewDto;
import com.bieliaiev.tarkov_pmc_tools.service.WeaponService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeaponController {
	
	private final WeaponService weaponService;

//	@GetMapping("/weapons")
//	public String showWeaponsPage(@RequestParam(required = false) String category, Model model) {
//		
//		List<WeaponDto> weapons;
//		try {
//			weapons = weaponService.filterWeaponsByDefault();
//			model.addAttribute("calibers", weaponService.getCalibers());
//			model.addAttribute("weapons", weapons);
//		} catch (IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
//		return "weapons";
//	}
	
	@GetMapping("/test")
	public String showTestPage(@RequestParam(required = false) String category, Model model) {
		List<WeaponViewDto> weapons;
		try {
			weapons = weaponService.getWeaponsForView();
			model.addAttribute("calibers", weaponService.getCalibers());
			model.addAttribute("weapons", weapons);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "test";
	}
}
