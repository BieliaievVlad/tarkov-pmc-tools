package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MeleePropertiesDto {

	private Integer slashDamage;
	private Integer stabDamage;
	private Float hitRadius;
}
