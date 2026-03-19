package com.capgemini.jwt_practice.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	//create secret key-> only  alphabets
	private final String SECRET_KEY="qwertuiopasdfgjklzxcvbqwertyyuiiiioopasdfghjklzxcvbmqwertuiop";
	
	public SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); 
	}
	
	public String createToken(String username) {
		return Jwts.builder().
		subject(username)
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+1000*60*2))
		.signWith(getKey()).compact();
	}
	
	public String getUserName(String token) {
		return Jwts.parser().verifyWith(getKey()).build()
		.parseSignedClaims(token).getPayload().getSubject(); 
	}
	
	public boolean isTokenExpired(String token) {
		return Jwts.parser().verifyWith(getKey())
		.build().parseSignedClaims(token)
		.getPayload().getExpiration().after(new Date()); 
	}
	
	public boolean isValidateToken(String user, String token) {
		String username = getUserName(token);
		return user.equals(username) && isTokenExpired(token); 
	}

}
