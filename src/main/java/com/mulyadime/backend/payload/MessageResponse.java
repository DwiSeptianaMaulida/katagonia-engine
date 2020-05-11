package com.mulyadime.backend.payload;

import lombok.Data;

@Data
public class MessageResponse {
	
	private String message;
	
	public MessageResponse(String message) {
		super();
		this.message = message;
	}

}
