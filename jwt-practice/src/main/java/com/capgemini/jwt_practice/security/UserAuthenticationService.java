package com.capgemini.jwt_practice.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.capgemini.jwt_practice.entity.UserInformation;
import com.capgemini.jwt_practice.repo.UserJpaRepository;

@Component
public class UserAuthenticationService implements UserDetailsService {
	
	private UserJpaRepository userjpa;
	
	

	public UserAuthenticationService(UserJpaRepository userjpa) {
		super();
		this.userjpa = userjpa;
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<UserInformation> info = userjpa.findById(email);
		if(info.isPresent()) {
			System.out.println("User Authentation service : "+email);
			return info.get();
			
		} else {
			throw new UsernameNotFoundException("User Name with "+email+"not found");
		}
	
	}
	
	

}
