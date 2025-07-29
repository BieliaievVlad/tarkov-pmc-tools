package com.bieliaiev.tarkov_pmc_tools.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryHandlerUtil {
	
	private final Logger logger = LoggerFactory.getLogger(QueryHandlerUtil.class)
;
	public String handleQuery(String rawQuery) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newBuilder().build();

		String query = QueryBuilderUtil.buildQuery(rawQuery);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Constants.TARKOV_API_URI))
				.header("Content-Type", Constants.HEADER)
				.header("Accept", Constants.HEADER)
				.POST(HttpRequest.BodyPublishers.ofString(query))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() == 200) {
			return response.body();
		} else {
		    logger.warn("Failed to fetch ammo data. Status: {}", response.statusCode());
		    return "";
		}
	}
}
