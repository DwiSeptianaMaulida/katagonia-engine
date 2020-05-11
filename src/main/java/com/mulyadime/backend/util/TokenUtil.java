package com.mulyadime.backend.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenUtil {
	
	@Value("${backend.app.jwtSecret}")
	private String tokenSecretKey;
	
	@Value("${backend.app.jwtExpirationMs}")
	private int tokenExpirationMs;

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(tokenSecretKey)
				.parseClaimsJws(token)
				.getBody().getSubject();
			
			return true;
			
		} catch (SignatureException e) {
			log.error("Invalid jwt signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		} catch (Exception e) {
			log.error("Exception: {}", e.getMessage());
		}
		
		return false;
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(tokenSecretKey)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}

	public String generateToken(Authentication auth) {
		UserDetails principal = (UserDetails) auth.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.setIssuedAt(DateUtil.getCurrentDate())
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpirationMs*1000))
				.signWith(SignatureAlgorithm.HS512, tokenSecretKey)
				.compact();
	}

}
