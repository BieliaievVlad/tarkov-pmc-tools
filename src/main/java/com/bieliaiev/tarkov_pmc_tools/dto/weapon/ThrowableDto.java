package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ThrowableDto {

	private String name;
	private String shortName;
	private String normalizedName;
	private String description;
	private String gridImageLink;
	private String image8xLink;
	private ThrowablePropertiesDto properties;
}
