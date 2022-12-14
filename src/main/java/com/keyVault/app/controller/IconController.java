package com.keyVault.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keyVault.app.dto.IconDTO;
@RestController
@RequestMapping("/ui/icon")
public class IconController {
	@GetMapping
	public ResponseEntity<?> getIcons (){
		return ResponseEntity.ok("ok");
	}
	@GetMapping
	public ResponseEntity<?> getIcon (){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createIcon(@RequestBody IconDTO icon){
		return ResponseEntity.ok("ok");
	}
	@PutMapping
	public ResponseEntity<?> updateIcon(@RequestBody IconDTO icon){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping
	public ResponseEntity<?> deleteIcon(){
		return ResponseEntity.ok("ok");
	}
}
