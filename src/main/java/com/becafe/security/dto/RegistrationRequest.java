package com.becafe.security.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

// made by Ayoub Youb with ❤️
@Data
@NoArgsConstructor
public class RegistrationRequest {

	@NotEmpty(message = "{registration_username_not_empty}")
	private String username;

	@NotEmpty(message = "{registration_password_not_empty}")
	private String password;

	private String firstName;

	private String lastName;

	private String address;

	private String phone;

	@Email(message = "{registration_email_is_not_valid}")
	@NotEmpty(message = "{registration_email_not_empty}")
	private String email;

}
