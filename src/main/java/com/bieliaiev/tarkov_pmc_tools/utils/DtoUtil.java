package com.bieliaiev.tarkov_pmc_tools.utils;

import java.util.List;

import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoUtil {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	
	public static List<WeaponDto> deepCopy(List<WeaponDto> weapons) {
	    return weapons.stream()
	            .map(w -> mapper.convertValue(w, WeaponDto.class))
	            .toList();
	}
}
