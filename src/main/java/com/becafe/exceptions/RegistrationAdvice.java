package com.becafe.exceptions;

import com.becafe.controller.RegisterController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// made by Ayoub Youb with ❤️
@RestControllerAdvice(basePackageClasses = RegisterController.class)
@Slf4j
public class RegistrationAdvice {

	@ExceptionHandler(RegistrationResponse.class)
	ResponseEntity<ValidationResponse> handleRegistrationException(RegistrationResponse exception) {
		final List<String> errorList = new ArrayList<>();
		errorList.add(exception.getErrorMessage());

		final ValidationResponse validationResponse = new ValidationResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), errorList);

		return ResponseEntity.status(validationResponse.getStatus()).body(validationResponse);
	}

}
