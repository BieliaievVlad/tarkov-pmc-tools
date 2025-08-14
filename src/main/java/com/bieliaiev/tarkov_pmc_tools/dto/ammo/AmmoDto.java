package com.bieliaiev.tarkov_pmc_tools.dto.ammo;

import java.util.List;

import com.bieliaiev.tarkov_pmc_tools.dto.BuyForDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AmmoDto {
	
	private String id;
	private String name;
	private String shortName;
	private String normalizedName;
	private String description;
	private String gridImageLink;
	private String image8xLink;
	private AmmoPropertiesDto properties;
	private List<BuyForDto> buyFor;
}
