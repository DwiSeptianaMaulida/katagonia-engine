package com.mulyadime.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.mulyadime.backend.payload.LoginRequest;
import com.mulyadime.backend.payload.MessageResponse;
import com.mulyadime.backend.payload.SignUpRequest;
import com.mulyadime.backend.payload.TokenResponse;
import com.mulyadime.backend.services.AuthenticationService;
import com.mulyadime.backend.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private AuthenticationService userService;
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,
			value = "")
	public ResponseEntity<?> auth(@Valid @RequestBody LoginRequest request) {
		log.info("{}", request.toString());
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		log.info("{}", auth.toString());
		String token = tokenUtil.generateToken(auth);
		UserDetails principal = (UserDetails) auth.getPrincipal();
		log.info("{}", principal.toString());
		List<String> roles = principal.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new TokenResponse(token, principal.getUsername(), roles));
	}
	
	@RequestMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,
			value = "/register")
	public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest request) {
		if (userService.findExistsByUsername(request.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		boolean result = userService.save(request);
		log.debug("Status register: {}", result);
		if (result = true) {
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		
		return ResponseEntity.ok(new MessageResponse("User fail registering!"));
	}

}
