package com.keyVault.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyVault.app.dto.CategoryDTO;
import com.keyVault.app.entity.Category;
import com.keyVault.app.exceptions.KeyVaultAppException;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.service.CategoryService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		List<Category> categoriesForUser = categoryRepository.findByUser_id(categoryDTO.getUser().getId());
		for(Category category:categoriesForUser) {
			if(category.getName().equals(categoryDTO.getName())== true) {
				return new ResponseEntity<>("The category with that name is already created",HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getCagory (@PathVariable(name = "id") int id){
		return ResponseEntity.ok(categoryService.findCategoryById(id));
	}
	@GetMapping
	public ResponseEntity<?> getCagories (){
		return ResponseEntity.ok(categoryService.findAllCategoriesForUser());
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable(name = "id") int id, @Valid @RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, id), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") int id){
		categoryService.deleteCategory(id);
		return new ResponseEntity<>("Category removed successfully", HttpStatus.OK);
	}
}
