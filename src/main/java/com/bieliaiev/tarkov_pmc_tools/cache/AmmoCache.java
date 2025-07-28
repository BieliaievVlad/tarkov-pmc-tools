package com.bieliaiev.tarkov_pmc_tools.cache;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class AmmoCache {

	private static final Duration CACHE_TTL = Duration.ofMinutes(10);
	private String ammoCachedJson;
	private Instant lastCacheUpdateTime;
	
	public Optional<String> getIfValid() {
		
		if (ammoCachedJson != null && lastCacheUpdateTime != null && Instant.now().isBefore(lastCacheUpdateTime.plus(CACHE_TTL))) {
			return Optional.of(ammoCachedJson);
		}
		return Optional.empty();
	}
	
	public void updateCache(String json) {
		this.ammoCachedJson = json;
		this.lastCacheUpdateTime = Instant.now();
	}
}
