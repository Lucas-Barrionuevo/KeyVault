package com.keyVault.app.service;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.dto.UserResponse;
import com.keyVault.app.entity.User;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.UserRepository;
import com.keyVault.app.security.TokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Service
public class UserService {
	public final static String ACCESS_TOKEN_SECRET = "$10$tTpH1I6caxJcn6uS.zWab.jiWcCQRp5SqklTezw2JUy21w3wBDRo";
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public UserResponse registerUser (UserDTO userDTO) {
		User user = mappingEntity(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedAt(new Date());
		user.setEnabled(true);
		User newUser = userRepository.save(user);
		String token = TokenUtils.createToken(newUser.getId());
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
		Date expirationDate = claims.getExpiration();
		UserDTO userDTO2 = mappingDTO(newUser);
		UserResponse responseUser = mappingResponse(userDTO2);
		responseUser.setToken(token);
		responseUser.setExpirationDate(expirationDate);
		return responseUser;
	}
	
	public UserResponse findUserById(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		UserDTO userDTO = mappingDTO(user);
		String token = TokenUtils.createToken(id);
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
		Date expirationDate = claims.getExpiration();
		UserResponse userResponse = mappingResponse(userDTO);
		userResponse.setToken(token);
		userResponse.setExpirationDate(expirationDate);
		return userResponse;
	}
	
	public UserResponse updateUser(UserDTO userDTO, int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		
		User updatedUser =userRepository.save(user);
		UserDTO userDTO2 = mappingDTO(user);
		UserResponse userResponse = mappingResponse(userDTO2);
		return userResponse;
	}
	
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setEnabled(false);
	}
	
	public UserDTO mappingDTO (User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	public UserResponse mappingResponse(UserDTO userDTO) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(userDTO.getId());
		userResponse.setEmail(userDTO.getEmail());
		userResponse.setCreatedAt(userDTO.getCreatedAt());
		return userResponse;
	}
	public User mappingEntity (UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
}
