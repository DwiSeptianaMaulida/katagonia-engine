package com.mulyadime.backend.services;

import java.util.HashMap;
import java.util.Hashtable;

public abstract class ServiceLocator {
	
	public static void parameterReset(HashMap<String, String> item) {
		item.clear();
	}
	
	public static void criteriaReset(Hashtable<String, Object> item) {
		item.clear();
	}
	
	protected String construct(String sql, Hashtable<String, Object> field, HashMap<String, String> param, String alias) {
		return sql;
	}

}
