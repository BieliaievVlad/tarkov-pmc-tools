package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeaponPropertiesDto {

    private String caliber;
    private Float ergonomics;
    private String[] fireModes;
    private Integer fireRate;
    private Integer recoilVertical;
    private Integer recoilHorizontal;
    private List<WeaponPresetDto> presets;
}
