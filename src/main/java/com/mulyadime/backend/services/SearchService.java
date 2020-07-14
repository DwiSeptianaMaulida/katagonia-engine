package com.mulyadime.backend.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mulyadime.backend.dto.SearchDTO;
import com.mulyadime.backend.mapper.SearchMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchService extends ServiceLocator {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	protected HashMap<String, String> params = new HashMap<>();
	
	private String query_find_all = "SELECT"
			+ " t.category AS term_category,"
			+ " t.name AS term_name,"
			+ " t.translation AS translation_name,"
			+ " a.category AS acronym_category,"
			+ " a.name AS acronym_name"
			+ " FROM term t"
			+ " LEFT JOIN acronym a"
			+ " ON LOWER(a.category) = LOWER(t.category)"
			+ " WHERE 1=1 AND ( LOWER(t.name) LIKE ? OR LOWER(a.name) LIKE ? OR LOWER(t.translation) LIKE ?)";
	
	public List<SearchDTO> getResult(String item) {
		parameterReset(params);
		params.put(SearchMapper.Field.TERM_NAME[0], item);
		params.put(SearchMapper.Field.ACRONYM_NAME[0], item);
		params.put(SearchMapper.Field.TRANSLATION_NAME[0], item);
		
		String newItem = "%" + item + "%";
		
		Object[] param = new Object[] { newItem, newItem, newItem };
		
		return jdbcTemplate.query(query_find_all, param, new SearchMapper());
	}

}
