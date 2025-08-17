package com.bieliaiev.tarkov_pmc_tools.dto.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ArmorDto {

	private String name;
	private String shortName;
	private String normalizedName;
	private String description;
	private String gridImageLink;
	private String image8xLink;
	private ArmorPropertiesDto properties;
}
