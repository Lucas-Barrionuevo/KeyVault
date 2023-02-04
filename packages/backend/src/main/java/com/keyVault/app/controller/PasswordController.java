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
import com.keyVault.app.entity.Password;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.repository.PasswordRepository;
import com.keyVault.app.repository.UserRepository;
import com.keyVault.app.security.TokenUtils;
import com.keyVault.app.service.PasswordService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/password")
public class PasswordController {
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private PasswordRepository passwordRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired 
	private UserRepository userRepository;
	@GetMapping
	public ResponseEntity<?> getPasswordsForUSer (HttpServletRequest request){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		return ResponseEntity.ok(passwordService.findAllPasswordsForUser(userId));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getPassword (@PathVariable(name = "id") int id, HttpServletRequest request){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		System.out.println("aca1");
		Password password = passwordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Password", "id", id));
		System.out.println("aca2");
		if (password.getUser().getId() != userId) {
			System.out.println("aca3");
			return new ResponseEntity<>("The password indicated does not match the username", HttpStatus.NOT_FOUND);
		}
		System.out.println("aca4");
		return ResponseEntity.ok(passwordService.findPasswordById(id));
	}
	@PostMapping
	public ResponseEntity<?> createPassword(@Valid @RequestBody PasswordDTO passwordDTO, HttpServletRequest request){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		passwordDTO.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)));
		if (passwordDTO.getCategoryName() != null){
			Optional<Category> enteredCategory = categoryRepository.findOneByUserIdAndName(userId, passwordDTO.getCategoryName());
			if(enteredCategory.isEmpty()){
				Category category = new Category();
				category.setUser(passwordDTO.getUser());
				category.setName(passwordDTO.getCategoryName());
				categoryRepository.save(category);
				passwordDTO.setCategory(category);
				return new ResponseEntity<>(passwordService.createPassword(passwordDTO,userId),HttpStatus.CREATED);
			}
			passwordDTO.setCategory(enteredCategory.get());
			return new ResponseEntity<>(passwordService.createPassword(passwordDTO,userId),HttpStatus.CREATED);
		}
		return new ResponseEntity<>(passwordService.createPassword(passwordDTO,userId),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePassword(@PathVariable(name = "id") int id,@Valid @RequestBody PasswordDTO passwordDTO, HttpServletRequest request){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		Password password = passwordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Password", "id", id));
		if (password.getUser().getId() != userId) {
			return new ResponseEntity<>("The password indicated does not match the username", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(passwordService.updatePassword(passwordDTO, id),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePassword(@PathVariable(name = "id") int id, HttpServletRequest request){
		TokenUtils tokenUtils = new TokenUtils();
		int userId = tokenUtils.getIdByToken(request);
		Password password = passwordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Password", "id", id));
		if (password.getUser().getId() != userId) {
			return new ResponseEntity<>("The password indicated does not match the username", HttpStatus.NOT_FOUND);
		}
		passwordService.deletePassword(id);
		return new ResponseEntity<>("Password removed successfully", HttpStatus.OK);
	}
}
