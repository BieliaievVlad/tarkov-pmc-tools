package com.bieliaiev.tarkov_pmc_tools.mappper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPresetPropertiesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesDto;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponPropertiesInterface;
import com.bieliaiev.tarkov_pmc_tools.dto.weapon.WeaponViewDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WeaponViewDtoMapper {
	
	private static final List<String> NORMALIZED_SUFFIXES = List.of(
		    "-default", "-lb", "-urbana", "-carbine", "-pecheneg"
		);
	private static final List<String> SUFFIXES = List.of(
			"Default", "LB", "Urbana", "Carbine", "Pecheneg"
			);
	private final Logger logger = LoggerFactory.getLogger(WeaponViewDtoMapper.class);

	public static List<WeaponViewDto> toViewDto(List<WeaponDto> defaultList, List<WeaponDto> descriptionList) {
		List<WeaponViewDto> views = new ArrayList<>();
		Map<String, WeaponDto> map = descriptionList.stream()
				.collect(Collectors.toMap(
						w -> w.getNormalizedName(), Function.identity()));
		
		for (WeaponDto w : defaultList) {
			WeaponDto dtoFromMap;
			Optional<WeaponDto> opt = map.entrySet().stream()
					.filter(entry -> entry.getKey().contains(stripNormalizedSuffix(w.getNormalizedName())))
					.map(Map.Entry :: getValue)
					.findFirst();
			if (opt.isPresent()) {
				dtoFromMap = opt.get();
			} else {
				logger.info("Dto from map is null for: " + w.getNormalizedName());
				continue;
			}
			WeaponViewDto view = new WeaponViewDto();
			WeaponPropertiesInterface dtoProps = dtoFromMap.getProperties();
			WeaponPropertiesInterface wProps = w.getProperties();
			view.setName(stripSuffix(w.getName()));
			view.setShortName(w.getShortName());
			view.setNormalizedName(stripNormalizedSuffix(w.getNormalizedName()));
			view.setCategory(dtoFromMap.getCategory().getNormalizedName());
			view.setDescription(dtoFromMap.getDescription());
			view.setGridImageLink(w.getGridImageLink());
			view.setImage8xLink(w.getImage8xLink());
			
			if (dtoProps instanceof WeaponPropertiesDto weaponProps) {
				view.setCaliber(weaponProps.getCaliber());
				view.setFireModes(weaponProps.getFireModes());
				view.setFireRate(weaponProps.getFireRate());
				view.setPresets(weaponProps.getPresets());
			}
			if (wProps instanceof WeaponPresetPropertiesDto presetProps) {
				view.setErgonomics(presetProps.getErgonomics());
				view.setRecoilVertical(presetProps.getRecoilVertical());
				view.setRecoilHorizontal(presetProps.getRecoilHorizontal());
				view.setMoa(presetProps.getMoa());
			}
			views.add(view);
		} 
		return views;
	}
	
	private String stripNormalizedSuffix(String name) {
	    for (String s : NORMALIZED_SUFFIXES) {
	        if (name.endsWith(s)) {
	            return name.substring(0, name.length() - s.length());
	        }
	    }
	    return name;
	}
	
	private String stripSuffix(String name) {
		for (String s : SUFFIXES) {
			if (name.endsWith(s)) {
				return name.substring(0, name.length() - s.length()).trim();
			}
		}
		return name;
	}
}
