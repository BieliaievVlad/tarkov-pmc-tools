package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import java.util.List;

import com.bieliaiev.tarkov_pmc_tools.dto.ContainsItemsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeaponPresetDto {

	private String name;
	private String inspectImageLink;
	private List<ContainsItemsDto> containsItems;
}
