package com.keyVault.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.keyVault.app.entity.User;
import com.keyVault.app.exceptions.KeyVaultAppException;
import com.keyVault.app.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mail) {
		User user = userRepository
		.findOneByMail(mail)
		.orElseThrow(()-> new KeyVaultAppException(HttpStatus.NOT_FOUND ,"The user with mail" + mail + "does not exist"));
		
		return new UserDetailsImpl(user);
	}
}