package com.becafe.dto;

import lombok.Data;

@Data
public class UserDto{
    private String userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean active;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String costumer;
    private String seller;
}
