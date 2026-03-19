package com.capgemini.jwt_practice.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capgemini.jwt_practice.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JWTUtil util;
	
	@Autowired
	UserAuthenticationService authenticationservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		String emailId = null;
		if(token != null && !token.isEmpty() && !token.isBlank()) {
		 emailId = util.getUserName(token);
		 System.out.println("Request came from token is valid "+emailId);
		} else {
			System.out.println("Once per Request Filter token is invalid "+token);
		}
		
		if(emailId != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails = authenticationservice.loadUserByUsername(emailId);
			
		boolean validToken	= util.isValidateToken(userdetails.getUsername(), token);
		System.out.println("Result of Valid Token : "+validToken);
		if(validToken) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			System.out.println("Invalid token : "+emailId);
		}
		
			
			
		}
		filterChain.doFilter(request, response); 
		
	}

}
