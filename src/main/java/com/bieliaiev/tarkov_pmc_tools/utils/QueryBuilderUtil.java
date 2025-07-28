package com.bieliaiev.tarkov_pmc_tools.utils;

import com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryBuilderUtil {

    public static String wrapQuery(String rawQuery) {
        String escaped = rawQuery.replace("\"", "\\\"");
        return String.format("{\"query\": \"%s\"}", escaped);
    }
//TODO написать 3-5 сложных запросов   
    public static String test() {
    	return  GraphQLQueryBuilder.query()
    		    .object("items", GraphQLQueryBuilder.object()
    		            .field("name")
    		            .field("shortName")
    		            .build()
    		        )
    		        .build();
    }
}
