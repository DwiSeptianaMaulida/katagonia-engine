package com.mulyadime.backend.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateUpdateUserProfileRequest implements Serializable {

	private static final long serialVersionUID = 8069260703681327496L;
	
	private Long userId;
	
	private boolean dosen;
	
	private String nidn;
	
	private boolean mahasiswa;
	
	private String nim;
	
	private String name;

}
