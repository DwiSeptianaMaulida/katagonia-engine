package com.mulyadime.backend.payload;

import lombok.Data;

@Data
public class SearchRequest {
	
	private Boolean language;
	
	private String search;

}
