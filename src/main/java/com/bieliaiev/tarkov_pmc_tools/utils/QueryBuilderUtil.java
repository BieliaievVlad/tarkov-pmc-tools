package com.bieliaiev.tarkov_pmc_tools.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryBuilderUtil {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String buildQuery(String rawQuery) {
		
		try {
			Map<String, Object> body = new HashMap<>();
			body.put("query", rawQuery);
			
			return mapper.writeValueAsString(body);
			
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error occured while building graphQL query.", e);
		}
	}
}
