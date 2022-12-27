package com.keyVault.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(name = "id") int id){
		return ResponseEntity.ok(userService.findUserById(id));
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok("ok");
	}
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.registerUser(userDTO),HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping
	public ResponseEntity<?> deleteUser(){
		return ResponseEntity.ok("ok");
	}
}
