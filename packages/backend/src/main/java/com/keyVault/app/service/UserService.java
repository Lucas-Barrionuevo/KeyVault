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
@Service
public class UserService {
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
		
		UserDTO userDTO2 = mappingDTO(newUser);
		UserResponse responseUser = mappingResponse(userDTO2);
		return responseUser;
	}
	
	public UserResponse findUserById(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		UserDTO userDTO = mappingDTO(user);
		UserResponse userResponse = mappingResponse(userDTO);
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
		UserResponse userResponse = null;
		userResponse.setEmail(userDTO.getEmail());
		userResponse.setCreatedAt(userDTO.getCreatedAt());
		return userResponse;
	}
	public User mappingEntity (UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
}
