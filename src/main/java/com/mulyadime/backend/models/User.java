package com.mulyadime.backend.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user_login")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user")
	private Long id;
	
	@Column(name = "username", length = 50)
	private String username;
	
	private String password;
	
	@Column(name = "is_active")
	private boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles = new HashSet<>();
	
}
