package com.mulyadime.backend.payload;

import lombok.Data;

@Data
public class CreateUpdateUserRequest {
	
	private Long userProfileId;
	
	private String username;
	
	private String password;

}
