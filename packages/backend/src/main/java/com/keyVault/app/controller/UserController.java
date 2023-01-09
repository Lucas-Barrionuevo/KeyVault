package com.keyVault.app.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.keyVault.app.security.JWTAuthResonseDTO;
import com.keyVault.app.security.JwtTokenProvider;
import com.keyVault.app.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody UserDTO userDTO){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthResonseDTO(token));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(name = "id") int id){
		return ResponseEntity.ok(userService.findUserById(id));
	}
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
		if (userRepository.findOneByEmail(userDTO.getEmail())== null) {
			return new ResponseEntity<>("The email entered is already in use", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.registerUser(userDTO),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable (name="id")int id, @Valid @RequestBody UserDTO userDTO){
		Optional<User> user = userRepository.findById(id);
		if (user== null) {
			return new ResponseEntity<>("the user with the entered id does not exist", HttpStatus.NOT_FOUND);
		}
		if (userDTO.getEnabled() == false) {
			return new ResponseEntity<>("The account does not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.updateUser(userDTO, id),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable (name="id")int id){
		Optional<User> user = userRepository.findById(id);
		if (user ==null) {
			return new ResponseEntity<>("the user with the entered id does not exist", HttpStatus.NOT_FOUND);
		}
		if (user.get().getEnabled() == false) {
			return new ResponseEntity<>("The account does not exist", HttpStatus.BAD_REQUEST);
		}
		userService.deleteUser(id);
		return new ResponseEntity<>("User removed successfully", HttpStatus.OK);
	}
}
