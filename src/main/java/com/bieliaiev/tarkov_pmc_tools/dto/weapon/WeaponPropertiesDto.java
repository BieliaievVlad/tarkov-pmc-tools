package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeaponPropertiesDto implements WeaponPropertiesInterface {

	@JsonProperty("__typename")
	private String typeName;
	
	@JsonProperty("default")
	private Boolean defaultFlag;
	
    private String caliber;
    private Float ergonomics;
    private String[] fireModes;
    private Integer fireRate;
    private Integer recoilVertical;
    private Integer recoilHorizontal;
    private List<WeaponPresetDto> presets;
    
    private Float moa;
}
