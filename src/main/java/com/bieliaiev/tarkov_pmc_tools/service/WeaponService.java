package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.cache.WeaponCache;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeaponService {

	private final CacheService service;
	private final WeaponCache weaponCache;
	private final Logger logger = LoggerFactory.getLogger(WeaponService.class);
	
	public List<WeaponDto> getWeaponsList() throws IOException, InterruptedException {
		
		List<WeaponDto> result;
		Optional<List<WeaponDto>> cachedList = weaponCache.getIfValid();

		if (cachedList.isPresent()) {
			logger.info("Cached weapons json returned.");
			result = cachedList.get();

		} else {
			logger.info("Cached weapon json updated.");
			result = service.cacheWeapons();
		}
		
		return result.stream().sorted(Comparator.comparing(
				weapon -> Optional.ofNullable(weapon.getProperties())
				.map(WeaponPropertiesDto::getCaliber)
				.orElse(null),
				Comparator.nullsLast(Comparator.naturalOrder())))
				.toList();
	}
	
	public List<WeaponDto> getWeaponByCaliber(String caliber) throws IOException, InterruptedException {
		return getWeaponsList().stream().filter(
				w -> Optional.ofNullable(w.getProperties())
				.map(p -> caliber.equals(p.getCaliber()))
				.orElse(false))
				.toList();
	}
}
