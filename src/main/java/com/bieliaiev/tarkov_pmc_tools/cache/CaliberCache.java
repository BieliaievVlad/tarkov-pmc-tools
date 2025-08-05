package com.bieliaiev.tarkov_pmc_tools.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static java.util.Map.entry;

@Component
public class CaliberCache {

	private Map<String, String> calibers = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(CaliberCache.class);
	
	public Map<String, String> getCalibersMapOrUpdate() {
		
		if (calibers.isEmpty()) {
			createCalibersMap();
			logger.info("Calibers cache updated.");
			return calibers;
			
		} else {
			logger.info("Calibers cache returned.");
			return calibers;
		}
	}
	
	private void createCalibersMap() {
		calibers = Map.ofEntries(
				entry("Caliber1143x23ACP", ".45 ACP"), 
				entry("Caliber127x33", ".50 AE"),
				entry("Caliber127x55", "12.7×55"), 
				entry("Caliber127x99", ".50 BMG"), 
				entry("Caliber12g", "12/70"),
				entry("Caliber20g", "20/70"), 
				entry("Caliber23x75", "23×75"), 
				entry("Caliber366TKM", ".366 TKM"),
				entry("Caliber46x30", "4.6×30"), 
				entry("Caliber545x39", "5.45×39"),
				entry("Caliber556x45NATO", "5.56×45"), 
				entry("Caliber57x28", "5.7×28"), 
				entry("Caliber68x51", "6.8×51"),
				entry("Caliber762x25TT", "7.62×25"), 
				entry("Caliber762x35", ".300 BLK"),
				entry("Caliber762x39", "7.62×39"), 
				entry("Caliber762x51", "7.62×51"),
				entry("Caliber762x54R", "7.62×54R"), 
				entry("Caliber86x70", ".338 LM"), 
				entry("Caliber9x18PM", "9×18"),
				entry("Caliber9x19PARA", "9×19"), 
				entry("Caliber9x21", "9×21"), 
				entry("Caliber9x33R", ".357 Mag"),
				entry("Caliber9x39", "9×39"));
	}
}
