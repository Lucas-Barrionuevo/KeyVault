package com.keyVault.app.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.dto.UserResponse;
import com.keyVault.app.entity.User;
import com.keyVault.app.repository.UserRepository;
import com.keyVault.app.security.TokenUtils;
import com.keyVault.app.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
		if (userRepository.findOneByEmail(userDTO.getEmail())== null) {
			return new ResponseEntity<>("The email entered is already in use", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.registerUser(userDTO),HttpStatus.CREATED);
	}
	@PutMapping()
	public ResponseEntity<?> updateUser(HttpServletRequest request, @Valid @RequestBody UserDTO userDTO){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		Optional<User> user = userRepository.findById(userId);
		if (user.get().getEnabled() == false) {
			return new ResponseEntity<>("The account does not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.updateUser(userDTO, userId),HttpStatus.OK);
	}
	@DeleteMapping()
	public ResponseEntity<?> deleteUser(HttpServletRequest request){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		Optional<User> user = userRepository.findById(userId);
		if (user.get().getEnabled() == false) {
			return new ResponseEntity<>("The account does not exist", HttpStatus.BAD_REQUEST);
		}
		userService.deleteUser(userId);
		return new ResponseEntity<>("User removed successfully", HttpStatus.OK);
	}
	@GetMapping("/me")
	public ResponseEntity<?> userResponse (HttpServletRequest request) {
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		Optional<User> user = userRepository.findById(userId);
		if (user.get().getEnabled() == false) {
			return new ResponseEntity<>("The account does not exist", HttpStatus.BAD_REQUEST);
		}
		UserResponse userResponse = userService.findUserById(userId);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
}
