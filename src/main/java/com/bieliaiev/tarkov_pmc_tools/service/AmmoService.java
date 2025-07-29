package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.cache.AmmoCache;
import com.bieliaiev.tarkov_pmc_tools.dto.ammo.AmmoDto;
import com.bieliaiev.tarkov_pmc_tools.dto.ammo.AmmoPropertiesDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmmoService {

	private final CacheService service;
	private final AmmoCache ammoCache;
	private Logger logger = LoggerFactory.getLogger(AmmoService.class);
	
	public List<AmmoDto> getAmmoList() throws IOException, InterruptedException {

		List<AmmoDto> result;
		Optional<List<AmmoDto>> cachedList = ammoCache.getIfValid();

		if (cachedList.isPresent()) {
			logger.info("Cached ammo json returned.");
			result = cachedList.get();

		} else {
			logger.info("Cached ammo json updated.");
			result = service.cacheAmmo();
		}

		return result.stream().sorted(Comparator.comparing(
				ammo -> Optional.ofNullable(ammo.getProperties())
				.map(AmmoPropertiesDto::getCaliber)
				.orElse(null),
				Comparator.nullsLast(Comparator.naturalOrder())))
				.toList();
	}

	public List<AmmoDto> sortAmmoList(String sort, String order) throws IOException, InterruptedException {
		
		List<AmmoDto> result = getAmmoList();
		Comparator<AmmoDto> comparator = null;
		
		if (sort == null) {
			comparator = Comparator.comparing(
					ammo -> Optional.ofNullable(ammo.getProperties())
					.map(AmmoPropertiesDto::getCaliber)
					.orElse(null),
					Comparator.nullsLast(Comparator.naturalOrder()));
			
		} else if (sort.equals("damage")) {
			comparator = Comparator.comparing(
					ammo -> Optional.ofNullable(ammo.getProperties())
					.map(AmmoPropertiesDto::getDamage)
					.orElse(null),
					Comparator.nullsLast(Comparator.naturalOrder()));
			
		} else if (sort.equals("armorDamage")) {
			comparator = Comparator.comparing(
					ammo -> Optional.ofNullable(ammo.getProperties())
					.map(AmmoPropertiesDto::getArmorDamage)
					.orElse(null),
					Comparator.nullsLast(Comparator.naturalOrder()));
			
		} else if (sort.equals("penetrationPower")) {
			comparator = Comparator.comparing(
					ammo -> Optional.ofNullable(ammo.getProperties())
					.map(AmmoPropertiesDto::getPenetrationPower)
					.orElse(null),
					Comparator.nullsLast(Comparator.naturalOrder()));
			
		} else if (sort.equals("name")) {
			comparator = Comparator.comparing(
					ammo -> Optional.ofNullable(ammo.getNormalizedName()).orElse(""),
					Comparator.nullsLast(String :: compareToIgnoreCase));
		}
		
		if (comparator != null && order.equalsIgnoreCase("desc")) {
			comparator = comparator.reversed();
		}
		
		return comparator != null
				? result.stream().sorted(comparator).toList() : result;
	}
	
	public AmmoDto getAmmoByNormalizedName(String normalizedName) throws IOException, InterruptedException {
	    return getAmmoList().stream()
	            .filter(ammo -> normalizedName.equals(ammo.getNormalizedName()))
	            .findFirst()
	            .orElseThrow(() -> new NoSuchElementException("Ammo with normalizedName " + normalizedName + " not found"));
	}
}
