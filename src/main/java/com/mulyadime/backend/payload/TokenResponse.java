package com.mulyadime.backend.payload;

import java.util.List;

import lombok.Data;

@Data
public class TokenResponse {
	
	private String type = "Bearer";
	
	private String token;
	
	private String username;
	
	private List<String> roles;
	
	public TokenResponse(String token, String username, List<String> roles) {
		super();
		this.token = token;
		this.username = username;
		this.roles = roles;
	}

}
