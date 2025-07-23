package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.utils.Constants;

@Service
public class GraphQLService {

	public String getAllItems() throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newBuilder().build();
		String query = """
				{ "query" : "{items {id name shortName description basePrice weight link iconLink baseImageLink inspectImageLink image512pxLink avg24hPrice changeLast48h changeLast48hPercent}}"}
				""";
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Constants.TARKOV_API_URI))
				.header("Content-Type", Constants.HEADER)
				.header("Accept", Constants.HEADER)
				.POST(HttpRequest.BodyPublishers.ofString(query))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
 	}
	
	public String getItemsByCategory(String category) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newBuilder().build();

		String query = String.format("""
				{"query" : "{items(categoryNames: [%s]) {name inspectImageLink shortName basePrice avg24hPrice changeLast48h changeLast48hPercent }}"}
				""", category);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Constants.TARKOV_API_URI))
				.header("Content-Type", Constants.HEADER)
				.header("Accept", Constants.HEADER)
				.POST(HttpRequest.BodyPublishers.ofString(query))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
	}
}
