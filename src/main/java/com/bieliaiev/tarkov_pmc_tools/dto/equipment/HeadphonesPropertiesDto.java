package com.bieliaiev.tarkov_pmc_tools.dto.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HeadphonesPropertiesDto {

	private Integer ambientVolume;
	private Integer compressorAttack;
	private Integer compressorGain;
	private Integer compressorRelease;
	private Integer compressorThreshold;
	private Float distanceModifier;
	private Float distortion;
	private Integer dryVolume;
}
