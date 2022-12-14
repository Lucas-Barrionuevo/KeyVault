package com.keyVault.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyVault.app.dto.PasswordDTO;

@RestController
@RequestMapping("/password")
public class PasswordController {
	@GetMapping
	public ResponseEntity<?> getPasswords (){
		return ResponseEntity.ok("ok");
	}
	@GetMapping
	public ResponseEntity<?> getPassword (){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createPassword(@RequestBody PasswordDTO passwordDTO){
		return ResponseEntity.ok("ok");
	}
	@PutMapping
	public ResponseEntity<?> updatePassword(@RequestBody PasswordDTO passwordDTO){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping
	public ResponseEntity<?> deletePassword(){
		return ResponseEntity.ok("ok");
	}
}
