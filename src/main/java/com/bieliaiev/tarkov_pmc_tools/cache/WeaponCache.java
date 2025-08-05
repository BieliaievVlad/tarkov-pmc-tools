package com.bieliaiev.tarkov_pmc_tools.cache;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class WeaponCache extends Cache<WeaponDto> {

	private List<WeaponDto> allWeaponsList;	
}
