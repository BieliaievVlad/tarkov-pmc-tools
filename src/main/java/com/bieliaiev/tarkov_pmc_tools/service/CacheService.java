package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.cache.AmmoCache;
import com.bieliaiev.tarkov_pmc_tools.cache.ArmbandCache;
import com.bieliaiev.tarkov_pmc_tools.cache.ArmorCache;
import com.bieliaiev.tarkov_pmc_tools.cache.HeadphonesCache;
import com.bieliaiev.tarkov_pmc_tools.cache.HeadwearCache;
import com.bieliaiev.tarkov_pmc_tools.cache.MeleeCache;
import com.bieliaiev.tarkov_pmc_tools.cache.ThrowableCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponCache;
import com.bieliaiev.tarkov_pmc_tools.dto.ItemDto;
import com.bieliaiev.tarkov_pmc_tools.dto.ammo.AmmoDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.ArmorDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.HeadphonesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.HeadwearDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.MeleeDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.ThrowableDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CacheService {

	private final AmmoCache ammoCache;
	private final WeaponCache weaponCache;
	private final MeleeCache meleeCache;
	private final ThrowableCache throwableCache;
	private final HeadphonesCache headphonesCache;
	private final ArmbandCache armbandCache;
	private final ArmorCache armorCache;
	private final HeadwearCache headwearCache;
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
		
		return result;
	}
	
	public List<MeleeDto> cacheMelee() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllMelee());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		
		List<MeleeDto> result = mapper.readerForListOf(MeleeDto.class).readValue(node);
		meleeCache.updateCache(result);
		logger.info("Melee cache updated.");
		
		return result;
	}
	
	public List<ThrowableDto> cacheThrowables() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllThrowables());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		
		List<ThrowableDto> result = mapper.readerForListOf(ThrowableDto.class).readValue(node);
		throwableCache.updateCache(result);
		logger.info("Throwable cache updatetd.");
		
		return result;
	}
	
	public List<HeadphonesDto> cacheHeadphones() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllHeadphones());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		
		List<HeadphonesDto> result = mapper.readerForListOf(HeadphonesDto.class).readValue(node);
		headphonesCache.updateCache(result);
		logger.info("Headphones cache updatetd.");
		
		return result;
	}
	
	public List<ItemDto> cacheArmbands() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllArmbands());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		
		List<ItemDto> result = mapper.readerForListOf(ItemDto.class).readValue(node);
		armbandCache.updateCache(result);
		logger.info("Armbands cache updated.");
		
		return result;
	}
	
	public List<ArmorDto> cacheArmor() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllArmor());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		
		List<ArmorDto> result = mapper.readerForListOf(ArmorDto.class).readValue(node);
		armorCache.updateCache(result);
		logger.info("Armor cache updated.");
		
		return result;
	}
	
	public List<HeadwearDto> cacheHeadwear() throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(service.getAllHeadwear());
		JsonNode node = root.path("data").path("items");
		
		if (node == null || node.isMissingNode() || !node.isArray()) {
			return List.of();
		}
		
		List<HeadwearDto> result = mapper.readerForListOf(HeadwearDto.class).readValue(node);
		headwearCache.updateCache(result);
		logger.info("Headwear cache updated.");
		
		return result;
	}
}
