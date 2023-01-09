package com.keyVault.app.controller;
import java.util.Optional;

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
import com.keyVault.app.dto.PasswordDTO;
import com.keyVault.app.entity.Category;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.service.PasswordService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/password")
public class PasswordController {
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private CategoryRepository categoryRepository;
	@GetMapping
	public ResponseEntity<?> getPasswordsForUSer (){
		return ResponseEntity.ok(passwordService.findAllPasswordsForUser());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getPassword (@PathVariable(name = "id") int id){
		return ResponseEntity.ok(passwordService.findPasswordById(id));
	}
	@PostMapping
	public ResponseEntity<?> createPassword(@Valid @RequestBody PasswordDTO passwordDTO){
		if (passwordDTO.getCategory() != null){
			Optional<Category> enteredCategory = categoryRepository.findById(passwordDTO.getCategory().getId());
			if(enteredCategory.isEmpty()){
				Category category = new Category();
				category.setUser(passwordDTO.getUser());
				category.setName("hola");
				categoryRepository.save(category);
				return new ResponseEntity<>(passwordService.createPassword(passwordDTO),HttpStatus.CREATED);
			}
			return new ResponseEntity<>(passwordService.createPassword(passwordDTO),HttpStatus.CREATED);
		}
		return new ResponseEntity<>(passwordService.createPassword(passwordDTO),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePassword(@PathVariable(name = "id") int id,@Valid @RequestBody PasswordDTO passwordDTO){
		return new ResponseEntity<>(passwordService.updatePassword(passwordDTO, id),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePassword(@PathVariable(name = "id") int id){
		passwordService.deletePassword(id);
		return new ResponseEntity<>("Password removed successfully", HttpStatus.OK);
	}
}
