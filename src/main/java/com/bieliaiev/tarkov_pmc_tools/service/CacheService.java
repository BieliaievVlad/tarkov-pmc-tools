package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.cache.AmmoCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponIconsCache;
import com.bieliaiev.tarkov_pmc_tools.dto.ammo.AmmoDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CacheService {

	private final AmmoCache ammoCache;
	private final WeaponCache weaponCache;
	private final WeaponIconsCache weaponIconsCache;
	private final GraphQLService service;
	private final Logger logger = LoggerFactory.getLogger(CacheService.class);
	
	public List<AmmoDto> cacheAmmo() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllAmmo());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		List<AmmoDto> result = mapper.readerForListOf(AmmoDto.class).readValue(node);
		ammoCache.updateCache(result);
		
		return result;
	}
	
	public List<WeaponDto> cacheWeapons() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllWeapons());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		List<WeaponDto> result = mapper.readerForListOf(WeaponDto.class).readValue(node);
		weaponCache.updateCache(result);
		logger.info("Weapon cache updated.");
		weaponIconsCache.updateWeaponIconsCache(result);
		logger.info("Weapon icons cache updated.");
		
		return result;
	}
}
