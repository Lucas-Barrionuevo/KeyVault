package com.keyVault.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.keyVault.app.dto.IconDTO;
import com.keyVault.app.service.IconService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/ui/icon")
public class IconController {
	@Autowired
	private IconService iconService;
	@GetMapping
	public ResponseEntity<?> getIcons (Authentication authentication){
		return ResponseEntity.ok(iconService.findAllIcons());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getIcon (@PathVariable(name = "id") int id){
		return ResponseEntity.ok(iconService.findIconById(id));
	}
	@PostMapping
	public ResponseEntity<?> createIcon(@Valid @RequestBody IconDTO iconDTO){
		return new ResponseEntity<>(iconService.createIcon(iconDTO),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateIcon(@PathVariable(name = "id") int id,@RequestBody IconDTO iconDTO){
		return new ResponseEntity<>(iconService.updateIcon(iconDTO, id),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteIcon(@PathVariable(name = "id") int id){
		iconService.deleteIcon(id);
		return new ResponseEntity<>("Icon removed successfully", HttpStatus.OK);
	}
}
