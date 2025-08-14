package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ThrowablePropertiesDto {

	private String type;
	private Float fuse;
	private Integer minExplosionDistance;
	private Integer maxExplosionDistance;
	private Integer fragments;
	private Integer contusionRadius;
}
