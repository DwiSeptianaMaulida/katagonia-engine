package com.mulyadime.backend.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
	
	private Long id;
	
	private Long userLogin;
	
	private String name;
	
	private boolean mahasiswa;
	
	private String nim;
	
	private boolean dosen;
	
	private String nidn;

}
