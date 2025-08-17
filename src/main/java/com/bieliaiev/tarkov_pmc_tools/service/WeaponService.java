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
import com.bieliaiev.tarkov_pmc_tools.cache.MeleeCache;
import com.bieliaiev.tarkov_pmc_tools.cache.ThrowableCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponPresetCache;
import com.bieliaiev.tarkov_pmc_tools.cache.WeaponViewCache;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.MeleeDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.ThrowableDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPresetDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPresetPropertiesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesInterface;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponViewDto;
import com.bieliaiev.tarkov_pmc_tools.mappper.WeaponViewDtoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeaponService {

	private final CacheService cacheService;
	private final WeaponCache weaponCache;
	private final CaliberCache caliberCache;
	private final MeleeCache meleeCache;
	private final ThrowableCache throwableCache;
	private final WeaponViewCache weaponViewCache;
	private final WeaponPresetCache weaponPresetCache;
	private final Logger logger = LoggerFactory.getLogger(WeaponService.class);

	public List<WeaponViewDto> getWeaponViewList() throws IOException, InterruptedException {
		Optional<List<WeaponViewDto>> opt = weaponViewCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Weapon view cache returned.");
			return opt.get();
		} else {
			return updateWeaponViewCache();
		}
	}
	
	public List<WeaponViewDto> filterWeaponsByCategory(String category) throws IOException, InterruptedException {
		if (category == null) {
			return getWeaponViewList();
			
		} else {
			return getWeaponViewList().stream()
					.filter(w -> w.getCategory().equals(category))
					.toList();
		}
	}
	
	public List<WeaponViewDto> getWeaponByCaliber(String caliber) throws IOException, InterruptedException {
		return getWeaponViewList().stream()
				.filter(w -> w.getCaliber().equals(caliber))
				.toList();
	}
	
	public WeaponViewDto getWeaponByName(String normalizedName) throws IOException, InterruptedException {
		return getWeaponViewList().stream()
				.filter(w -> w.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Weapon with given normalized name ({}) not founded.", normalizedName);
					return new WeaponViewDto();
				});
	}

	public Map<String, String> getCalibers() {
		return caliberCache.getCalibersMapOrUpdate();
	}
	
	public WeaponPresetDto getPresetByName(String normalizedName) throws IOException, InterruptedException {
		return getPresets().stream()
				.filter(p -> p.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Preset with given normalized name ({}) not founded.", normalizedName);
					return new WeaponPresetDto();
				});
	}
	
	public List<MeleeDto> getMeleeList() throws IOException, InterruptedException {
		Optional<List<MeleeDto>> opt = meleeCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Melee cache returned.");
			return opt.get();
		} else {
			return cacheService.cacheMelee();
		}
	}
	
	public List<ThrowableDto> getThrowablesList() throws IOException, InterruptedException {
		Optional<List<ThrowableDto>> opt = throwableCache.getIfValid();
		
		if (opt.isPresent()) {
			logger.info("Throwables cache returned.");
			return opt.get();
		} else {
			return cacheService.cacheThrowables();
		}
	}
	
	public MeleeDto getMeleeByName(String normalizedName) throws IOException, InterruptedException {
		return getMeleeList().stream()
				.filter(i -> i.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Melee weapon with given normalized name ({}) is not found.", normalizedName);
					return new MeleeDto();
				});
	}
	
	public ThrowableDto getThrowableByName(String normalizedName) throws IOException, InterruptedException {
		return getThrowablesList().stream()
				.filter(i -> i.getNormalizedName().equals(normalizedName))
				.findFirst()
				.orElseGet(() -> {
					logger.warn("Throwable with given normalized name ({}) is not found.", normalizedName);
					return new ThrowableDto();
				});
	}
	
	private List<WeaponPresetDto> getPresets() throws IOException, InterruptedException {
		Optional<List<WeaponPresetDto>> opt = weaponPresetCache.getIfValid();
		
		if(opt.isPresent()) {
			logger.info("Weapon preset cache returned.");
			return opt.get();
		} else {
			return updatePresets();
		}
	}
	
	private List<WeaponPresetDto> updatePresets() throws IOException, InterruptedException {
		List<WeaponPresetDto> presets = new ArrayList<>();
		List<WeaponDto> list = getWeaponsList();
		
		for (WeaponDto w : list) {
			
			if(w.getProperties() instanceof WeaponPropertiesDto props) {
				List<WeaponPresetDto> p = props.getPresets();
				if (p != null && !p.isEmpty()) {
					presets.addAll(p);
				}
			}
		}
		weaponPresetCache.updateCache(presets);
		logger.info("Weapon preset cache updated.");
		return presets;
	}
	
	private List<WeaponDto> getWeaponsList() throws IOException, InterruptedException {
		
		Optional<List<WeaponDto>> optList = weaponCache.getIfValid();
		
		if (optList.isPresent()) {
			logger.info("Cached weapon list returned.");
			return optList.get();
		} else {
			return cacheService.cacheWeapons();
		}
	}
	
	private List<WeaponDto> filterWeaponsByBaseWeapon() throws IOException, InterruptedException {
		return getWeaponsList().stream()
				.filter(w -> w.getDescription() != null && !w.getDescription().isEmpty())
				.toList();
	}
	
	private List<WeaponDto> filterWeaponsByDefaultFlag() throws IOException, InterruptedException {		
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
	
	private List<WeaponViewDto> updateWeaponViewCache() throws IOException, InterruptedException {
		List<WeaponDto> defaultList = filterWeaponsByDefaultFlag();
		List<WeaponDto> baseList = filterWeaponsByBaseWeapon();
		List<WeaponViewDto> result = WeaponViewDtoMapper.toViewDto(defaultList, baseList);

		logger.info("Weapon view cache updated.");
		weaponViewCache.updateCache(result);
		return result;
	}
}
