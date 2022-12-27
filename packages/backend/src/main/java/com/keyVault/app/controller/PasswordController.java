package com.keyVault.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/{id}")
	public ResponseEntity<?> getPassword (@PathVariable(name = "id") long id){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createPassword(@RequestBody PasswordDTO passwordDTO){
		return ResponseEntity.ok("ok");
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePassword(@PathVariable(name = "id") long id,@RequestBody PasswordDTO passwordDTO){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePassword(@PathVariable(name = "id") long id){
		return ResponseEntity.ok("ok");
	}
}
