package com.bieliaiev.tarkov_pmc_tools.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ItemDto {

	private String id;
	private String name;
	private String shortName;
	private String description;
	private String inspectImageLink;
	private String link;
	private Integer basePrice;
	private Integer avg24hPrice;
	private Float changeLast48h;
	private Float changeLast48hPercent;
}
