package com.becafe.dto;

import com.becafe.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto{
    private String userID;
    @NotEmpty(message = "{registration_username_not_empty}")
    private String username;
    @NotEmpty(message = "{registration_password_not_empty}")
    private String password;
    @NotEmpty(message = "{registration_email_not_empty}")
    @Email(message = "{registration_email_is_not_valid}")
    private String email;
    @NotNull(message = "{registration_role_is_not_null}")
    private Role role;
    private boolean active;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String costumer;
    private String seller;
}
