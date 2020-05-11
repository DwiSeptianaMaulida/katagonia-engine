package com.mulyadime.backend.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class SignUpRequest implements Serializable {
	
	private static final long serialVersionUID = 5764529867199443649L;

	private String username;
	
	private String password;

}
