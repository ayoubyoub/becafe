package com.becafe.security.dto;

import com.becafe.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// made by Ayoub Youb with ❤️
@Data
@NoArgsConstructor
public class RegistrationRequest {

	@NotEmpty(message = "{registration_name_not_empty}")
	private String name;

	@Email(message = "{registration_email_is_not_valid}")
	@NotEmpty(message = "{registration_email_not_empty}")
	private String email;

	@NotEmpty(message = "{registration_username_not_empty}")
	private String username;

	@NotEmpty(message = "{registration_password_not_empty}")
	private String password;

	@NotNull(message = "{registration_role_is_not_null}")
	private Role role;

}
