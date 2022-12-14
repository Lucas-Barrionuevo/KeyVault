package com.keyVault.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keyVault.app.dto.CategoryDTO;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@GetMapping
	public ResponseEntity<?> getCagories (){
		return ResponseEntity.ok("ok");
	}
	@GetMapping
	public ResponseEntity<?> getCagory (){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody CategoryDTO category){
		return ResponseEntity.ok("ok");
	}
	@PutMapping
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO category){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping
	public ResponseEntity<?> deleteCategory(){
		return ResponseEntity.ok("ok");
	}
}
