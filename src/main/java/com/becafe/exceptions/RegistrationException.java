package com.becafe.exceptions;

import lombok.*;

// made by Ayoub Youb with ❤️
@Data
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	private final String errorMessage;

}
