package com.keyVault.app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailOrId) throws UsernameNotFoundException {
		if(emailOrId.contains("@")) {
			com.keyVault.app.entity.User user = userRepository.findOneByEmail(emailOrId).orElseThrow(() -> new ResourceNotFoundException("User", "email", 0));
			return new UserDetailsImpl(user);
		}
		com.keyVault.app.entity.User user = userRepository.findById(Integer.valueOf(emailOrId)).orElseThrow(() -> new ResourceNotFoundException("User", "id", 0));
		return new UserDetailsImpl(user);
	}
}
