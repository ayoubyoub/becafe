package com.becafe.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Ayoub Youb
@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	private final String errorMessage;

}
