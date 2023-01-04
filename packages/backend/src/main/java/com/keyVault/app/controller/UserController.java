package com.keyVault.app.controller;
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
import com.keyVault.app.entity.User;
import com.keyVault.app.repository.UserRepository;
import com.keyVault.app.security.TokenUtils;
import com.keyVault.app.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired(required=false)
	private TokenUtils tokenUtils;
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(name = "id") int id){
		return ResponseEntity.ok(userService.findUserById(id));
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO){
		User user = userService.mappingEntity(userDTO);
		String token =  tokenUtils.createToken(user.getId());
		return ResponseEntity.ok(token);
	}
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
		if (userRepository.findOneByMail(userDTO.getMail())== null) {
			return new ResponseEntity<>("The email entered is already in use", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.registerUser(userDTO),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable (name="id")int id, @Valid @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.updateUser(userDTO, id),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable (name="id")int id){
		userService.deleteUser(id);
		return new ResponseEntity<>("User removed successfully", HttpStatus.OK);
	}
}
