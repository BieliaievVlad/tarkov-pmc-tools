package com.bieliaiev.tarkov_pmc_tools.dto.ammo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AmmoPropertiesDto {
	private String caliber;
	private Boolean tracer;
	private String tracerColor;
	private String ammoType;
	private Integer projectileCount;
	private Integer damage;
	private Integer armorDamage;
	private Integer penetrationPower;
	private Float accuracyModifier;
	private Float recoilModifier;
	private Float initialSpeed;
	private Float lightBleedModifier;
	private Float heavyBleedModifier;
	private Float heatFactor;
	private Float ballisticCoeficient;
	private Float bulletDiameterMilimeters;
	private Float bulletMassGrams;
}