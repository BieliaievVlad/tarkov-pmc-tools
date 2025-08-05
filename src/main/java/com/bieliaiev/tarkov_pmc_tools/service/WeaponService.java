package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.cache.CaliberCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponCache;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponIconsCache;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPresetPropertiesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesInterface;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponViewDto;
import com.bieliaiev.tarkov_pmc_tools.mappper.WeaponViewDtoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeaponService {

	private final CacheService service;
	private final WeaponCache weaponCache;
	private final CaliberCache caliberCache;
	private final WeaponIconsCache iconsCache;
	private final Logger logger = LoggerFactory.getLogger(WeaponService.class);
	
	public List<WeaponDto> getWeaponsList() throws IOException, InterruptedException {
		
		Optional<List<WeaponDto>> optList = weaponCache.getIfValid();
		
		if (optList.isPresent()) {
			logger.info("Cached weapon list returned.");
			return optList.get();
		} else {
			return service.cacheWeapons();
		}
	}
	
	public List<WeaponViewDto> getWeaponsForView() throws IOException, InterruptedException {
		List<WeaponDto> defaultList = filterWeaponsByDefaultFlag();
		List<WeaponDto> baseList = filterWeaponsByBaseWeapon();
		
		return WeaponViewDtoMapper.someName(defaultList, baseList);
	}
	
	public List<WeaponDto> filterWeaponsByDefaultFlag() throws IOException, InterruptedException {		
		return getWeaponsList().stream()
				.filter(w -> {
					WeaponPropertiesInterface props = w.getProperties();
					if (props instanceof WeaponPresetPropertiesDto presetProps) {
						return Boolean.TRUE.equals(presetProps.getDefaultFlag());
					}
					return false;
				})
				.toList();
	}
	
	public List<WeaponDto> filterWeaponsByBaseWeapon() throws IOException, InterruptedException {
		return getWeaponsList().stream()
				.filter(w -> w.getDescription() != null && !w.getDescription().isEmpty())
				.toList();
	}

	public List<WeaponDto> getWeaponByCaliber(String caliber) throws IOException, InterruptedException {
		return getWeaponsList().stream()
				.filter(w -> {
					WeaponPropertiesInterface props = w.getProperties();
					if (props instanceof WeaponPropertiesDto baseProps) {
						return caliber.equals(baseProps.getCaliber());
					}
					return false;
				})
				.toList();
	}

	public Map<String, String> getCalibers() {
		return caliberCache.getCalibersMapOrUpdate();
	}
}
