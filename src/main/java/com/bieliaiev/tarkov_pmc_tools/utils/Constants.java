package com.bieliaiev.tarkov_pmc_tools.utils;

import java.util.Map;
import java.util.function.Predicate;

import com.bieliaiev.tarkov_pmc_tools.dto.ItemDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

	 public static final String TARKOV_API_URI = "https://api.tarkov.dev/graphql";
	 public static final String HEADER = "application/json";
	 public static final Map<String, Predicate<ItemDto>> FILTERS = Map.of(
			 "Ammo", item -> true,
			 "Weapon", item -> item.getShortName() != null && item.getShortName().contains("Default"),
			 "Armor", item -> item.getShortName() != null && !item.getShortName().contains("Default"),
			 "ChestRig", item -> item.getShortName() != null && !item.getShortName().contains("Default"),
			 "Headwear", item -> true);
}
