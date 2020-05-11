package com.mulyadime.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 8271971788272898771L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_profile")
	private Long id;
	
	@Column(name = "fk_user")
	private Long userId;
	
	@Column(name = "is_dosen")
	private boolean dosen;
	
	@Column(name = "is_mahasiswa")
	private boolean mahasiswa;
	
	@Column(length = 15)
	private String nim;
	
	@Column(length = 15)
	private String nidn;
	
	@Column(length = 200)
	private String name;

}
