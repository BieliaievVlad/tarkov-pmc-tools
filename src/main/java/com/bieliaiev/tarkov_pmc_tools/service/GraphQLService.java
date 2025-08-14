package com.bieliaiev.tarkov_pmc_tools.service;

import java.io.IOException;
import org.springframework.stereotype.Service;

import com.bieliaiev.tarkov_pmc_tools.utils.Queries;
import com.bieliaiev.tarkov_pmc_tools.utils.QueryHandlerUtil;

@Service
public class GraphQLService {

	public String getAllAmmo() throws IOException, InterruptedException {
		return QueryHandlerUtil.handleQuery(Queries.ALL_AMMO);
	}
	
	public String getAllWeapons() throws IOException, InterruptedException {
		return QueryHandlerUtil.handleQuery(Queries.ALL_WEAPONS);
	}
	
	public String getAllMelee() throws IOException, InterruptedException {
		return QueryHandlerUtil.handleQuery(Queries.ALL_MELEE);
	}
	
	public String getAllThrowables() throws IOException, InterruptedException {
		return QueryHandlerUtil.handleQuery(Queries.ALL_GRENADES);
	}
}
