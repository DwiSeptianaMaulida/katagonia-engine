package com.mulyadime.backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mulyadime.backend.dto.UserProfileDTO;

public class UserProfileMapper implements RowMapper<UserProfileDTO> {
	
	public static class Field {
		public static String[] PK_USER_PROFILE = {
				"PK_USER_PROFILE"
		};
		public static String[] FK_USER = {
				"FK_USER"
		};
		public static String[] NAME = {
				"NAME"
		};
		public static String[] IS_MAHASISWA = {
				"IS_MAHASISWA"
		};
		public static String[] NIM = {
				"NIM"
		};
		public static String[] IS_DOSEN = {
				"IS_DOSEN"
		};
		public static String[] NIDN = {
				"NIDN"
		};
	}

	@Override
	public UserProfileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserProfileDTO item = new UserProfileDTO();
		item.setId(rs.getLong(Field.PK_USER_PROFILE[0]));
		item.setUserLogin(rs.getLong(Field.FK_USER[0]));
		
		item.setName(rs.getString(Field.NAME[0]));
		
		item.setMahasiswa(rs.getBoolean(Field.IS_MAHASISWA[0]));
		item.setNim(rs.getString(Field.NIM[0]));
		
		item.setDosen(rs.getBoolean(Field.IS_DOSEN[0]));
		item.setNidn(rs.getString(Field.NIDN[0]));
		
		return item;
	}

}
