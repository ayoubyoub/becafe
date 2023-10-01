package com.becafe.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

// made by Ayoub Youb with ❤️
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponse {

	private HttpStatus status;

	private LocalDateTime time;

	private List<String> message;

}
