package com.bieliaiev.tarkov_pmc_tools.utils;

import java.util.Map;
import java.util.function.Predicate;
import static java.util.Map.entry;

import com.bieliaiev.tarkov_pmc_tools.dto.ItemDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

	 public static final String TARKOV_API_URI = "https://api.tarkov.dev/graphql";
	 public static final String HEADER = "application/json";
	    public static final Map<String, Predicate<ItemDto>> FILTERS = Map.ofEntries(
	            entry("Ammo", item -> true),
	            entry("Shotgun", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("AssaultRifle", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("AssaultCarbine", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("SMG", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("Machinegun", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("MarksmanRifle", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("SniperRifle", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("Handgun", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("Revolver", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("GrenadeLauncher", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("RocketLauncher", item -> item.getShortName() != null && item.getShortName().contains("Default")),
	            entry("Armor", item -> item.getShortName() != null && !item.getShortName().contains("Default")),
	            entry("ChestRig", item -> item.getShortName() != null && !item.getShortName().contains("Default")),
	            entry("Headwear", item -> true),
	            entry("Headphones", item -> true)
	        );
}
