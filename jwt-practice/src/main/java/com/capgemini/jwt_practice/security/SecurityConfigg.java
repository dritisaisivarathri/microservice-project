package com.capgemini.jwt_practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigg {
	
	
	@Autowired
	JwtFilter jwtfilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
	
	@Bean
	AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception{
		System.out.println("Authentication Manager initialized");
	return configuration.getAuthenticationManager();
	
}

	@Bean
    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception{
    	return http.csrf(csrf->csrf.disable())
    	.cors(cors->cors.disable())
    	.authorizeHttpRequests(reqs->reqs.requestMatchers("/public/**").permitAll()
    	.anyRequest().authenticated())
        .addFilterBefore(jwtfilter,UsernamePasswordAuthenticationFilter.class).build();
    }
	
//	@Bean
//	public BCryptPasswordEncoder encode() {
//		return new BCryptPasswordEncoder();
//	}

}
