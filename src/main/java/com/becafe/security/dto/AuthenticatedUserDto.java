package com.becafe.security.dto;

import com.becafe.model.UserRole;
import lombok.*;

// made by Ayoub Youb with ❤️
@Data
@NoArgsConstructor
public class AuthenticatedUserDto {

	private String name;

	private String username;

	private String password;

	private UserRole userRole;

}
