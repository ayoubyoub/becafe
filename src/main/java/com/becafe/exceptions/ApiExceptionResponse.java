package com.becafe.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

// made by Ayoub Youb with ❤️
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponse {

	private String message;

	private HttpStatus status;

	private LocalDateTime time;

}
