package com.keyVault.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Test {
	@GetMapping("/status")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("ON");
	}
	
}
