package com.capgemini.jwt_practice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.jwt_practice.UserInformationDTO;
import com.capgemini.jwt_practice.entity.UserInformation;
import com.capgemini.jwt_practice.repo.UserJpaRepository;

@Service
public class UserService {
	
	private UserJpaRepository userjpa;
	
//	@Autowired
//	BCryptPasswordEncoder encodepassword;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


	public UserService(UserJpaRepository userjpa) {
		this.userjpa = userjpa;
	}
	
	public UserInformation createUser(UserInformationDTO dto) {
		UserInformation info = new UserInformation();
		info.setEmailId(dto.getEmailId());
		info.setName(dto.getName());
		//info.setPassword(dto.getPassword());
		info.setPhone(dto.getPhone());
		info.setPassword(passwordEncoder.encode(dto.getPassword()));
		return userjpa.save(info); 
		
	}
	
	

}
