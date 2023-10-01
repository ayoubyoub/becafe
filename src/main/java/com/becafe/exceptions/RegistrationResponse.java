package com.becafe.exceptions;

import lombok.*;

// made by Ayoub Youb with ❤️
@Data
@RequiredArgsConstructor
public class RegistrationResponse extends RuntimeException {

	private final String errorMessage;

}
