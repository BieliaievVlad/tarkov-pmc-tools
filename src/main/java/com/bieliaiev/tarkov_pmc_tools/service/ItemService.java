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
	
}
