package com.mulyadime.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mulyadime.backend.dto.UserProfileDTO;
import com.mulyadime.backend.mapper.UserProfileMapper;
import com.mulyadime.backend.models.UserProfile;
import com.mulyadime.backend.payload.CreateUpdateUserProfileRequest;
import com.mulyadime.backend.repository.UserProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProfileService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String query_find_all = "SELECT * FROM (" + 
			"	SELECT " + 
			"        pk_user_profile " + 
			"        , fk_user " + 
			"        , name " + 
			"        , is_mahasiswa " + 
			"        , nim " + 
			"        , is_dosen " + 
			"        , nidn " + 
			"    FROM demo.user_profile " + 
			") up WHERE 1 = 1";
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	

	public UserProfile save(CreateUpdateUserProfileRequest request) {
		log.debug("Parameter body: {}", request.toString());
		UserProfile item = new UserProfile();
		item.setDosen(request.isDosen());
		item.setNidn(request.getNidn());
		item.setMahasiswa(request.isMahasiswa());
		item.setNim(request.getNim());
		item.setName(request.getName().trim().toUpperCase());
		
		return userProfileRepository.save(item);
	}

	@SuppressWarnings("unused")
	public UserProfile update(Long id, CreateUpdateUserProfileRequest request) {
		UserProfile item = userProfileRepository.findById(id).get();
		
		log.debug("Parameter body: {}", request.toString());
		log.debug("Existing data: {}", item.toString());
		
		if (item != null) {
			item.setDosen(request.isDosen());
			item.setNidn(request.getNidn());
			item.setMahasiswa(request.isMahasiswa());
			item.setNim(request.getNim());
			item.setName(request.getName().trim().toUpperCase());

			return userProfileRepository.save(item);
		}
		
		return null;
	}

	@SuppressWarnings("unused")
	public boolean delete(Long id) {
		UserProfile item = userProfileRepository.findById(id).get();
		log.debug("Existing data: {}", item.toString());
		
		if (item != null) {
			userProfileRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

	public List<UserProfile> findAll() {
		List<UserProfile> result = new ArrayList<>();
		userProfileRepository.findAll().iterator().forEachRemaining(result::add);
		
		return result;
	}

	public UserProfile findById(Long id) {
		return userProfileRepository.findById(id).get();
	}

	public List<UserProfile> findAllMahasiswa() {
		List<UserProfile> result = new ArrayList<>();
		userProfileRepository.findByMahasiswa(true).iterator().forEachRemaining(result::add);
		
		return result;
	}

	public List<UserProfile> findAllDosen() {
		List<UserProfile> result = new ArrayList<>();
		userProfileRepository.findByDosen(true).iterator().forEachRemaining(result::add);
		
		return result;
	}

	public List<UserProfileDTO> findByFilter(String column, String value) {
		StringBuffer sb = new StringBuffer();
		sb.append(query_find_all);
		if (column == UserProfileMapper.Field.PK_USER_PROFILE[0]) {
			sb.append(" AND ").append(UserProfileMapper.Field.PK_USER_PROFILE[0]).append("=").append(value);
		}
		if (column == UserProfileMapper.Field.NIM[0]) {
			sb.append(" AND ").append(UserProfileMapper.Field.NIM[0]).append("=").append(value);
		}
		
		String query = sb.toString();
		return jdbcTemplate.query(query, new UserProfileMapper());
	}

}
