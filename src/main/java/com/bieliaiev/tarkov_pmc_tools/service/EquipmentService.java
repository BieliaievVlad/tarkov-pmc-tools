package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.cache.ArmbandCache;
import com.bieliaiev.tarkov_pmc_tools.cache.ArmorCache;
import com.bieliaiev.tarkov_pmc_tools.cache.HeadphonesCache;
import com.bieliaiev.tarkov_pmc_tools.cache.HeadwearCache;
import com.bieliaiev.tarkov_pmc_tools.dto.ItemDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.ArmorDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.HeadphonesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.equipment.HeadwearDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentService {

	private final CacheService cacheService;
	private final HeadphonesCache headphonesCache;
	private final ArmbandCache armbandCache;
	private final ArmorCache armorCache;
	private final HeadwearCache headwearCache;
	private final Logger logger = LoggerFactory.getLogger(EquipmentService.class);
	
	public List<HeadphonesDto> getHeadphonesList() throws IOException, InterruptedException {
		Optional<List<HeadphonesDto>> opt = headphonesCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Headphones cache returned.");
			return opt.get();
		} else {
			return cacheService.cacheHeadphones();
		}
	}
	
	public HeadphonesDto getHeadphoneByName(String normalizedName) throws IOException, InterruptedException {
		return getHeadphonesList().stream()
				.filter(i -> i.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Headphones with given normalized name ({}) is not found.", normalizedName);
					return new HeadphonesDto();
				});
	}
	
	public List<ItemDto> getArmbandsList() throws IOException, InterruptedException {
		Optional<List<ItemDto>> opt = armbandCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Armbands cache returned.");
			return opt.get();
		} else {
			return cacheService.cacheArmbands();
		}
	}
	
	public ItemDto getArmbandByName(String normalizedName) throws IOException, InterruptedException {
		return getArmbandsList().stream()
				.filter(i -> i.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Armband with given normalized name ({}) is not found.", normalizedName);
					return new ItemDto();
				});
	}
	
	public List<ArmorDto> filterArmorList() throws IOException, InterruptedException {
		return getArmorList().stream()
				.filter(i -> !i.getNormalizedName().contains("default"))
				.toList();
	}
	
	public ArmorDto getArmorByName (String normalizedName) throws IOException, InterruptedException {
		return getArmorList().stream()
				.filter(i -> i.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Armor with given normalized name ({}) is not found.", normalizedName);
					return new ArmorDto();
				});
	}
	
	public List<HeadwearDto> getHelmetsList() throws IOException, InterruptedException {
		return getHeadwearList().stream()
				.filter(i -> i.getProperties() != null && i.getProperties().getArmorType() != null)
				.toList();
	}
	
	public HeadwearDto getHeadwearByName(String normalizedName) throws IOException, InterruptedException {
		return getHeadwearList().stream()
				.filter(i -> i.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Headwear with given normalized name ({}) is not found.", normalizedName);
					return new HeadwearDto();
				});
	}
	
	
	private List<ArmorDto> getArmorList() throws IOException, InterruptedException {
		Optional<List<ArmorDto>> opt = armorCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Armor cache returned.");
			return opt.get();
		} else {
			return cacheService.cacheArmor();
		}
	}
	
	private List<HeadwearDto> getHeadwearList() throws IOException, InterruptedException {
		Optional<List<HeadwearDto>> opt = headwearCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Headwear cache returned.");
			return opt.get();
		} else {
			return cacheService.cacheHeadwear();
		}
	}
}
