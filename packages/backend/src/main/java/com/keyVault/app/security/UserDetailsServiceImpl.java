package com.keyVault.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keyVault.app.entity.User;
import com.keyVault.app.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		User user = userRepository
		.findOneByMail(mail)
		.orElseThrow(()-> new UsernameNotFoundException("The user with mail" + mail + "does not exist"));
		
		return new UserDetailsImpl(user);
	}
}
