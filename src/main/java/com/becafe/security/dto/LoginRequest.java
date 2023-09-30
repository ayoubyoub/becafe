package com.becafe.security.dto;

import lombok.Getter;
import lombok.*;

import javax.validation.constraints.NotEmpty;

// made by Ayoub Youb with ❤️
@Data
@NoArgsConstructor
public class LoginRequest {

	@NotEmpty(message = "{login_username_not_empty}")
	private String username;

	@NotEmpty(message = "{login_password_not_empty}")
	private String password;

}
