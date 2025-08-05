package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY,
		property = "__typename",
		visible = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = WeaponPropertiesDto.class, name = "ItemPropertiesWeapon"),
		@JsonSubTypes.Type(value = WeaponPresetPropertiesDto.class, name = "ItemPropertiesPreset")})
public interface WeaponPropertiesInterface {

}
