package com.keyVault.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keyVault.app.dto.UserDTO;
@RestController
@RequestMapping("/auth")
public class UserController {
	@GetMapping("/me")
	public ResponseEntity<?> getUser(){
		return ResponseEntity.ok("ok");
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok("ok");
	}
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok("ok");
	}
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping
	public ResponseEntity<?> deleteUser(){
		return ResponseEntity.ok("ok");
	}
}
