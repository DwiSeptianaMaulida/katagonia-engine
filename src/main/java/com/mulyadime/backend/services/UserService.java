package com.mulyadime.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mulyadime.backend.models.User;
import com.mulyadime.backend.models.UserProfile;
import com.mulyadime.backend.payload.CreateUpdateUserRequest;
import com.mulyadime.backend.repository.UserProfileRepository;
import com.mulyadime.backend.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	public boolean autoCreateUser(CreateUpdateUserRequest request) {
		UserProfile userProfile = userProfileService.findById(request.getUserProfileId());
		if (userProfile != null) {
			User item = new User();
			item.setActive(true);
			item.setUsername(request.getUsername());
			item.setPassword(passwordEncoder.encode(request.getPassword()));
			item = userRepository.save(item);
			
			userProfile.setUserId(item.getId());
			userProfile = userProfileRepository.save(userProfile);

			return true;
		}
		
		return false;
	}

}
