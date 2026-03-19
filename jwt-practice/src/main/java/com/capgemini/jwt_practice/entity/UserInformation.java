package com.capgemini.jwt_practice.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserInformation  implements UserDetails{
	@Id
	private String emailId;
	private String name;
	private String password;
	private String phone;
	
	
	public UserInformation(String emailId, String name, String password, String phone) {
		this.emailId = emailId;
		this.name = name;
		this.password = password;
		this.phone = phone;
	}
	
	public UserInformation() {
		
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getEmailId();
	}

	
	

}
