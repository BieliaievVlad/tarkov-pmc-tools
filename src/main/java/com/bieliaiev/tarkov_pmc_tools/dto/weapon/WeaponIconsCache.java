package com.bieliaiev.tarkov_pmc_tools.dto.weapon;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class WeaponIconsCache {

	private Map<String, String> iconsCache;
	
	public Map<String, String> getIconsCache() {
		return iconsCache;
	}
	
	public void updateIconsCache(List<WeaponDto> list) {
		
		iconsCache.clear();
		
	}
}
