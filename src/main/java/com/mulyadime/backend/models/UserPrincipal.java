package com.mulyadime.backend.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -2924772899358646671L;
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
		this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
