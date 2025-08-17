package com.bieliaiev.tarkov_pmc_tools.dto.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ArmorPropertiesDto {

	@JsonProperty("class")
	private Integer armorClass;
	private String armorType;
	private String[] zones;
}
