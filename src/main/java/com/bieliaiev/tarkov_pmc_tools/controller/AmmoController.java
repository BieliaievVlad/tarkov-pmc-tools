package com.bieliaiev.tarkov_pmc_tools.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bieliaiev.tarkov_pmc_tools.dto.BuyForDto;
import com.bieliaiev.tarkov_pmc_tools.dto.ammo.AmmoDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.bieliaiev.tarkov_pmc_tools.service.AmmoService;
import com.bieliaiev.tarkov_pmc_tools.service.WeaponService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AmmoController {

	private final AmmoService ammoService;
	private final WeaponService weaponService;
	
	@GetMapping("/allAmmo")
	public String showAllAmmoPage(
			@RequestParam (required = false) String caliber,
			@RequestParam (required = false) String sort,
			@RequestParam (required = false, defaultValue = "asc") String order,
			Model model) {
		
		try {
			List<AmmoDto> allAmmo = ammoService.sortAmmoList(ammoService.filterAmmoByCaliber(caliber), sort, order);
			model.addAttribute("allAmmo", allAmmo);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		model.addAttribute("caliber", caliber);
		model.addAttribute("sort", sort);
		model.addAttribute("order", order);

		return "allAmmo";
	}
	
	@GetMapping("/ammo")
	public String showAmmoPage(@RequestParam String normalizedName, Model model) {
		
		AmmoDto ammo;
		
		try {
			ammo = ammoService.getAmmoByNormalizedName(normalizedName);
			List<BuyForDto> buyFor = ammo.getBuyFor();
			List<WeaponDto> weapons = weaponService.getWeaponByCaliber(ammo.getProperties().getCaliber());
			Map<String, String> calibers = weaponService.getCalibers();
			
			model.addAttribute("calibers", calibers);
			model.addAttribute("ammo", ammo);
			model.addAttribute("buyFor", buyFor);
			model.addAttribute("weapons", weapons);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return "ammo";
	}
}
