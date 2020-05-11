package com.mulyadime.backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mulyadime.backend.services.AuthenticationService;
import com.mulyadime.backend.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private AuthenticationService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = parseToken(request);
			log.info("Get parse token: ", token);
			if (token != null && tokenUtil.validateToken(token)) {
				String username = tokenUtil.getUsernameFromToken(token);
				UserDetails principal = userService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String parseToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
