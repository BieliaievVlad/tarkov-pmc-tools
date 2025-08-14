package com.bieliaiev.tarkov_pmc_tools.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bieliaiev.tarkov_pmc_tools.dto.weapon.MeleeDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.ThrowableDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponViewDto;
import com.bieliaiev.tarkov_pmc_tools.service.AmmoService;
import com.bieliaiev.tarkov_pmc_tools.service.WeaponService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeaponController {
	
	private final AmmoService ammoService;
	private final WeaponService weaponService;
	
	@GetMapping("/weapons")
	public String showWeaponsPage(@RequestParam(required = false) String category, Model model) {
		List<WeaponViewDto> weapons;
		try {
			weapons = weaponService.filterWeaponsByCategory(category);
			model.addAttribute("calibers", weaponService.getCalibers());
			model.addAttribute("weapons", weapons);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "weapons";
	}
	
	@GetMapping("/allMelee")
	public String showAllMeleePage(Model model) {
		try {
			List<MeleeDto> melee = weaponService.getMeleeList();
			model.addAttribute("melee", melee);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "allMelee";
	}
	
	@GetMapping("/throwables")
	public String showThrowablesPage(Model model) {
		
		try {
			List<ThrowableDto> throwables = weaponService.getThrowablesList();
			model.addAttribute("throwables", throwables);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}	
		return "throwables";
	}
	
	@GetMapping("/weapon")
	public String showWeaponPage(@RequestParam String normalizedName, Model model) {
		try {
			WeaponViewDto weapon = weaponService.getWeaponByName(normalizedName);
			model.addAttribute("weapon", weapon);
			model.addAttribute("ammoList", ammoService.filterAmmoByCaliber(weapon.getCaliber()));
			model.addAttribute("calibers", weaponService.getCalibers());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "weapon";
	}
	
	@GetMapping("/preset")
	public String showPresetPage(@RequestParam String normalizedName, Model model) {
		
		try {
			model.addAttribute("preset", weaponService.getPresetByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "preset";
	}
	
	@GetMapping("/melee")
	public String showMeleePage(@RequestParam String normalizedName, Model model) {
		
		try {
			model.addAttribute("melee", weaponService.getMeleeByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return "melee";
	}
	
	@GetMapping("/throwable")
	public String showThrowablePage(@RequestParam String normalizedName, Model model) {
		
		try {
			model.addAttribute("throwable", weaponService.getThrowableByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "throwable";
	}
}
