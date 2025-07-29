package com.bieliaiev.tarkov_pmc_tools.cache;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public abstract class Cache<T> {

	private static final Duration CACHE_TTL = Duration.ofMinutes(60);
	private List<T> cachedList;
	private Instant lastCacheUpdateTime;
	
	public Optional<List<T>> getIfValid() {
		
		if (cachedList != null && lastCacheUpdateTime != null && Instant.now().isBefore(lastCacheUpdateTime.plus(CACHE_TTL))) {
			return Optional.of(cachedList);
		}
		return Optional.empty();
	}
	
	public void updateCache(List<T> list) {
		this.cachedList = list;
		this.lastCacheUpdateTime = Instant.now();
	}
}
