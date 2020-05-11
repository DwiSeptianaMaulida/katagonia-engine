package com.mulyadime.backend.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mulyadime.backend.models.Role;
import com.mulyadime.backend.models.User;
import com.mulyadime.backend.models.UserPrincipal;
import com.mulyadime.backend.payload.SignUpRequest;
import com.mulyadime.backend.repository.RoleRepository;
import com.mulyadime.backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findOneByUsername(String username) {
		return userRepository.findOneByUsername(username).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findOneByUsername(username);
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		log.debug("{}", authorities.toString());
		
		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authorities);
	}

	public boolean findExistsByUsername(String username) {
		return false;
	}

	public boolean save(SignUpRequest request) {
		if (request != null ) {
			User item = new User();
			item.setUsername(request.getUsername());
			item.setPassword(passwordEncoder.encode(request.getPassword()));
			item.setActive(true);
			Set<Role> role = new HashSet<>();
			role.add(roleRepository.findOneByName("USER").get());
			item.setRoles(role);
			
			userRepository.save(item);
			return true;	
		}
		
		return false;
	}

}
