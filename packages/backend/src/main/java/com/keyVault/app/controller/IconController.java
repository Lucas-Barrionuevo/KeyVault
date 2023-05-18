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
import com.keyVault.app.dto.IconDTO;
@RestController
@RequestMapping("/ui/icon")
public class IconController {
	@GetMapping
	public ResponseEntity<?> getIcons (){
		return ResponseEntity.ok("ok");
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getIcon (@PathVariable(name = "id") long id){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createIcon(@RequestBody IconDTO icon){
		return ResponseEntity.ok("ok");
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateIcon(@PathVariable(name = "id") long id,@RequestBody IconDTO icon){
		return ResponseEntity.ok("ok");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteIcon(@PathVariable(name = "id") long id){
		return ResponseEntity.ok("ok");
	}
}
