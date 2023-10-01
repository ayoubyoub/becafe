package com.becafe.controller;

import com.becafe.model.Role;
import com.becafe.security.dto.RegistrationRequest;
import com.becafe.security.dto.RegistrationResponse;
import com.becafe.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// made by Ayoub Youb with ❤️
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<RegistrationResponse> registrationRequestAdmin(@Valid @RequestBody RegistrationRequest registrationRequest) {

		final RegistrationResponse registrationResponse = userService.registration(registrationRequest, Role.ADMIN);

		return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}

}
