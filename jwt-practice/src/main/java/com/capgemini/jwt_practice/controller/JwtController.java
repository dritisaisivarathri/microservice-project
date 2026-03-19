package com.capgemini.jwt_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jwt_practice.LoginDTO;
import com.capgemini.jwt_practice.UserInformationDTO;
import com.capgemini.jwt_practice.entity.UserInformation;
import com.capgemini.jwt_practice.service.UserService;
import com.capgemini.jwt_practice.util.JWTUtil;

@RestController
public class JwtController {
	
	@Autowired
	JWTUtil util;
	
	@Autowired 
	private UserService service;
	
	@Autowired
	private AuthenticationManager authentication;
	
	
	@GetMapping("/{username}")
	//http://localhost:4050/dritisai
	public String user(@PathVariable String username) {
		return util.createToken(username);
	}
	
	@GetMapping("/get-user/{token}")
	//http://localhost:4050/get-user/eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOi
	//Jkcml0aXNhaSIsImlhdCI6MTc3MzcyNTE5NywiZXhwIjoxNzczNzI1MzE3fQ.uuVIMcUGq6shwKUqaVevXeAnBWdxjF748zT7bWlscesOCMTV663QBGMSKnoNVMeV
	public String getUserName(@PathVariable String token) { 
		return util.getUserName(token);
	}
	
	@GetMapping("/validate/{user}/{token}")
	//http://localhost:4050/validate/dritisai/eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOi
	//Jkcml0aXNhaSIsImlhdCI6MTc3MzcyODk5MywiZXhwIjoxNzczNzI5MTEzfQ.lM1yReljc1CkDPWE0ty5OnNnrKxdFft2coCQ_4Z424yJdZlYlUKzTaKI1HYzVzL3
	public boolean isTokenValid(@PathVariable String user, @PathVariable String token) {
		return util.isValidateToken(user, token);
	}
	
	
	@PostMapping("/public/create")
	public UserInformation createUser(@RequestBody UserInformationDTO dto) {
		return service.createUser(dto);
	}
	
	@PostMapping("/public/user/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
		UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(dto.getEmailId(), dto.getPassword());
		Authentication auth = authentication.authenticate(credentials);
		System.out.println(auth.getCredentials());
		
		String token = util.createToken(dto.getEmailId()); 
		
		HttpHeaders responseHeader = new HttpHeaders();
		
		responseHeader.add("Authorization", token);
		
		return new ResponseEntity<String>("Welcome to Home : "+dto.getEmailId(),HttpStatus.ACCEPTED).ok(token); 
		
	}
} 
