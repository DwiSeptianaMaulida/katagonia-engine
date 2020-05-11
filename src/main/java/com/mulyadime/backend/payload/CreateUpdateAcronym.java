package com.mulyadime.backend.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateUpdateAcronym implements Serializable {
	
	private static final long serialVersionUID = 1535269356186110717L;

	private String name;
	
	private String category;
	
	private String indonesia;
	
	private String english;

}
