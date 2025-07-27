package com.bieliaiev.tarkov_pmc_tools.service.ammo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.utils.Constants;

@Service
public class AmmoGraphQLService {

    private static final Duration CACHE_TTL = Duration.ofMinutes(10);

    private String cachedAmmoJson;
    private Instant lastCacheUpdateTime;
    private Logger logger = LoggerFactory.getLogger(AmmoGraphQLService.class);
	
	public String getAllAmmo() throws IOException, InterruptedException {
		
		if (cachedAmmoJson != null && lastCacheUpdateTime != null && Instant.now().isBefore(lastCacheUpdateTime.plus(CACHE_TTL))) {
			logger.info("Cached ammo json returned.");
			return cachedAmmoJson;
		}
		
		HttpClient client = HttpClient.newBuilder().build();
		
		String query = """
				{"query": "{items (categoryNames: [Ammo]) {name shortName normalizedName description inspectImageLink buyFor{vendor {name} price currency} properties {...on ItemPropertiesAmmo {caliber tracer tracerColor ammoType projectileCount damage armorDamage penetrationPower accuracyModifier recoilModifier initialSpeed lightBleedModifier heavyBleedModifier heatFactor ballisticCoeficient bulletDiameterMilimeters bulletMassGrams}}}}"}
				    """;
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Constants.TARKOV_API_URI))
				.header("Content-Type", Constants.HEADER)
				.header("Accept", Constants.HEADER)
				.POST(HttpRequest.BodyPublishers.ofString(query))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() == 200) {
		    cachedAmmoJson = response.body();
		    lastCacheUpdateTime = Instant.now();
		    logger.info("Cached ammo json updated.");
		} else {
		    logger.warn("Failed to fetch ammo data. Status: {}", response.statusCode());
		}
		
		return cachedAmmoJson;
	}
}
