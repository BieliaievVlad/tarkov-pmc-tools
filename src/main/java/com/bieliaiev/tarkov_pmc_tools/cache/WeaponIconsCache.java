package com.bieliaiev.tarkov_pmc_tools.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;

@Component
public class WeaponIconsCache {

	private final Map<String, String> iconsCache = new HashMap<>();

	public void updateWeaponIconsCache(List<WeaponDto> list) {

		iconsCache.clear();
		
		for (WeaponDto w : list) {
			if (w.getNormalizedName().contains("default")) {
				String name = w.getNormalizedName().replaceFirst("-default", "");
				String iconLink = w.getInspectImageLink();
				iconsCache.put(name, iconLink);
			}
		}
	}
	
	public Map<String, String> getIconsCache() {
		return iconsCache;
	}
}
