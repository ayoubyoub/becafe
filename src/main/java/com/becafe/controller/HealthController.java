package com.becafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// made by Ayoub Youb with ❤️
@RestController
public class HealthController {

	@GetMapping("/health")
	public ResponseEntity<String> sayHello() {

		return ResponseEntity.ok("Working Hot as Chili Sauce - made by Ayoub Youb with ❤️");
	}

}
