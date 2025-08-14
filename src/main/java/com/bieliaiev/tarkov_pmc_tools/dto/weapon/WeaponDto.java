package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import java.util.List;

import com.bieliaiev.tarkov_pmc_tools.dto.BuyForDto;
import com.bieliaiev.tarkov_pmc_tools.dto.ItemCategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeaponDto {

    private String name;
    private String shortName;
    private String normalizedName;
    private String description;
    private String gridImageLink;
    private String image8xLink;
    private ItemCategoryDto category;
    private WeaponPropertiesInterface properties;
    private List<BuyForDto> buyFor;   
}
