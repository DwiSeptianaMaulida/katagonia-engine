package com.mulyadime.backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.jdbc.core.RowMapper;

import com.mulyadime.backend.dto.SearchDTO;
import com.mulyadime.backend.services.ServiceLocator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchMapper extends ServiceLocator implements RowMapper<SearchDTO> {
	
	public static class Field {
		public static String[] TERM_CATEGORY = {
				"TERM_CATEGORY"
		};
		public static String[] TERM_NAME = {
				"TERM_NAME"
		};
		public static String[] TRANSLATION_NAME = {
				"TRANSLATION_NAME"
		};
		public static String[] ACRONYM_CATEGORY = {
				"ACRONYM_CATEGORY"
		};
		public static String[] ACRONYM_NAME = {
				"ACRONYM_NAME"
		};
	}
	
	public Hashtable<String, Object> criteria = new Hashtable<>();
	public void createCriteria() {
		criteria.put(Field.TERM_CATEGORY[0], Field.TERM_CATEGORY);
		criteria.put(Field.TERM_NAME[0], Field.TERM_NAME);
		criteria.put(Field.TRANSLATION_NAME[0], Field.TRANSLATION_NAME);
		criteria.put(Field.ACRONYM_CATEGORY[0], Field.ACRONYM_CATEGORY);
		criteria.put(Field.ACRONYM_NAME[0], Field.ACRONYM_NAME);
	}

	@Override
	public SearchDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SearchDTO item = new SearchDTO();
		item.setTermCategory(rs.getString(Field.TERM_CATEGORY[0]));
		item.setTermName(rs.getString(Field.TERM_NAME[0]));
		
		item.setTranslationName(rs.getString(Field.TRANSLATION_NAME[0]));
		
		item.setAcronymCategory(rs.getString(Field.ACRONYM_CATEGORY[0]));
		item.setAcronymName(rs.getString(Field.ACRONYM_NAME[0]));
		
		log.info("{}", item.toString());
		
		return item;
	}
	
	public String findByWhereClause(String sql, HashMap<String, String> params) {
		criteriaReset(criteria);
		createCriteria();
		
		return construct(sql, criteria, params, "");
	}

}
