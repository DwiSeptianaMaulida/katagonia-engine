package com.mulyadime.backend.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateUpdateTerm implements Serializable {
	
	private static final long serialVersionUID = -7243412798954496892L;
	
	private String name;
	
	private String translation;
	
	private String category;
	
	private String language;

}
