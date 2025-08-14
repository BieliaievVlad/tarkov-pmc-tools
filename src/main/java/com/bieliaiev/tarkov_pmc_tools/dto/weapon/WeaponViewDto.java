package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import java.util.List;

import lombok.Data;

@Data
public class WeaponViewDto {

	private String name;
	private String shortName;
	private String normalizedName;
	private String category;
	private String description;
	private String gridImageLink;
	private String image8xLink;
	private String caliber;
	private String[] fireModes;
	private Integer fireRate;
	private Float ergonomics;
	private Integer recoilVertical;
	private Integer recoilHorizontal;
	private Float moa;
	private List<WeaponPresetDto> presets;
}
