package com.bieliaiev.tarkov_pmc_tools.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bieliaiev.tarkov_pmc_tools.dto.ItemDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.ArmorDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.HeadphonesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.HeadwearDto;
import com.bieliaiev.tarkov_pmc_tools.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EquipmentController {

	private final EquipmentService equipService;
	
	@GetMapping("/allHeadphones")
	public String showHeadphonesPage(Model model) {
		List<HeadphonesDto> phones;
		try {
			phones = equipService.getHeadphonesList();
			model.addAttribute("phones", phones);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "allHeadphones";
	}
	
	@GetMapping("/headphones")
	public String showHeadphonePage(@RequestParam String normalizedName, Model model) {
		try {
			model.addAttribute("phone", equipService.getHeadphoneByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "headphones";
	}
	
	@GetMapping("/armbands")
	public String showArmbandsPage(Model model) {
		List<ItemDto> armbands;
		try {
			armbands = equipService.getArmbandsList();
			model.addAttribute("armbands", armbands);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "armbands";
	}
	
	@GetMapping("/armband")
	public String showArmbandPage(@RequestParam String normalizedName, Model model) {
		try {
			model.addAttribute("armband", equipService.getArmbandByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "armband";
	}
	
	@GetMapping("/allArmor")
	public String showAllArmorPage(Model model) {
		List<ArmorDto> armor;
		
		try {
			armor = equipService.filterArmorList();
			model.addAttribute("armor", armor);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "allArmor";
	}
	
	@GetMapping("/armor")
	public String showArmorPage(@RequestParam String normalizedName, Model model) {
		
		try {
			model.addAttribute("armor", equipService.getArmorByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "armor";
	}
	
	@GetMapping("/allHelmets")
	public String showAllHelmetsPage(Model model) {
		
		List<HeadwearDto> helmets;
		try {
			helmets = equipService.getHelmetsList();
			model.addAttribute("helmets", helmets);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return "allHelmets";
	}
	
	@GetMapping("/helmet")
	public String showHelmetPage(@RequestParam String normalizedName, Model model) {
		try {
			model.addAttribute("helmet", equipService.getHeadwearByName(normalizedName));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "helmet";
	}
}
