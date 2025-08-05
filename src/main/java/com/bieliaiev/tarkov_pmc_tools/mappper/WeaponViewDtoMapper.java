package com.bieliaiev.tarkov_pmc_tools.mappper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	private final Logger logger = LoggerFactory.getLogger(WeaponViewDtoMapper.class);
	
//TODO переделать под shortName
	public static List<WeaponViewDto> someName(List<WeaponDto> defaultList, List<WeaponDto> descriptionList) {
		List<WeaponViewDto> views = new ArrayList<>();
		Map<String, WeaponDto> map = defaultList.stream()
				.collect(Collectors.toMap(
						w -> w.getNormalizedName().replace("-default", ""), Function.identity()));
		
		for (WeaponDto w : descriptionList) {
			WeaponDto dtoFromMap = map.get(w.getNormalizedName());
			if (dtoFromMap == null) {
				logger.info("Dto from map is null for: " + w.getNormalizedName());
				continue;
			}
			WeaponViewDto view = new WeaponViewDto();
			WeaponPropertiesInterface wProps = w.getProperties();
			WeaponPropertiesInterface dtoProps = dtoFromMap.getProperties();
			view.setName(w.getName());
			view.setShortName(w.getShortName());
			view.setNormalizedName(w.getNormalizedName());
			view.setCategory(w.getCategory().getNormalizedName());
			view.setDescription(w.getDescription());
			view.setInspectImageLink(dtoFromMap.getInspectImageLink());
			
			if (wProps instanceof WeaponPropertiesDto weaponProps) {
				view.setCaliber(weaponProps.getCaliber());
				view.setFireModes(weaponProps.getFireModes());
				view.setFireRate(weaponProps.getFireRate());
				view.setPresets(weaponProps.getPresets());
			}
			if (dtoProps instanceof WeaponPresetPropertiesDto presetProps) {
				view.setErgonomics(presetProps.getErgonomics());
				view.setRecoilVertical(presetProps.getRecoilVertical());
				view.setRecoilHorizontal(presetProps.getRecoilHorizontal());
				view.setMoa(presetProps.getMoa());
			}
			views.add(view);
		} 
		return views;
	}
}
