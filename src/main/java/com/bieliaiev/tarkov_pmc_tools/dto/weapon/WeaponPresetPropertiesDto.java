package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeaponPresetPropertiesDto implements WeaponPropertiesInterface {

	@JsonProperty("__typename")
	private String typeName;
	
	@JsonProperty("default")
	private Boolean defaultFlag;
	
	private Float ergonomics;
	private Integer recoilVertical;
	private Integer recoilHorizontal;
	private Float moa;
}
