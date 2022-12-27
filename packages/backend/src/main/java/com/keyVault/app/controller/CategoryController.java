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
import com.keyVault.app.dto.CategoryDTO;
import com.keyVault.app.service.CategoryService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<?> getCagories (){
		return ResponseEntity.ok(categoryService.findAllCategories());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getCagory (@PathVariable(name = "id") int id){
		return ResponseEntity.ok(categoryService.findCategoryById(id));
	}
	@PostMapping
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable(name = "id") int id, @Valid @RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, id), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") int id){
		categoryService.deleteCategory(id);
		return new ResponseEntity<>("category removed successfully", HttpStatus.OK);
	}
}
