package com.keyVault.app.service;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.entity.User;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO registerUser (UserDTO userDTO) {
		User user = mappingEntity(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedAt(new Date());
		user.setEnabled(true);
		User newUser = userRepository.save(user);
		
		UserDTO responseUser = mappingDTO(newUser);
		return responseUser;
	}
	
	public UserDTO findUserById(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return mappingDTO(user);
	}
	
	public UserDTO updateUser(UserDTO userDTO, int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		
		User updatedUser =userRepository.save(user);
		return mappingDTO(updatedUser);
	}
	
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setEnabled(false);
	}
	
	public UserDTO mappingDTO (User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	public User mappingEntity (UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
}
