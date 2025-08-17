package com.bieliaiev.tarkov_pmc_tools.dto.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HeadwearPropertiesDto {

	@JsonProperty("class")
	private Integer armorClass;
	private String armorType;
	private Integer durability;
	private Float speedPenalty;
	private Float turnPenalty;
	private Float ergoPenalty;
	private String deafening;
	private Boolean blocksHeadset;
}
