package com.bieliaiev.tarkov_pmc_tools.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ItemCategoryDto {

	private String name;
	private String normalizedName;
}
