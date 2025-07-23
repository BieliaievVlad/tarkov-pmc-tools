package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.dto.ItemDto;
import com.bieliaiev.tarkov_pmc_tools.utils.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final GraphQLService service;
	
	public List<ItemDto> getItems() throws IOException, InterruptedException {
		String response = service.getAllItems();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response);
		JsonNode itemsNode = root.path("data").path("items");
		
		if (itemsNode == null || itemsNode.isMissingNode() || !itemsNode.isArray()) {
		    return List.of();
		}
		
		List<ItemDto> result = mapper.readerForListOf(ItemDto.class).readValue(itemsNode);
		
		return result.stream()
				.sorted(Comparator.comparing(ItemDto :: getName))
				.toList();
	}
	
	public List<ItemDto> getItemsByCategory(String category) throws IOException, InterruptedException {
		
		String response = service.getItemsByCategory(category);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response);
		JsonNode itemNode = root.path("data").path("items");
		
		if (itemNode == null || itemNode.isMissingNode() || !itemNode.isArray()) {
		    return List.of();
		}
		
		List<ItemDto> result = mapper.readerForListOf(ItemDto.class).readValue(itemNode);
		
		return result.stream()
				.filter(Constants.FILTERS.get(category))
				.sorted(Comparator.comparing(ItemDto :: getShortName))
				.toList();
	}
}
