package com.becafe.service;

import com.becafe.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(String id);

    UserDto saveUser(UserDto userDto);

    UserDto saveCostumer(UserDto userDto);

    UserDto saveSeller(UserDto userDto);

    void deleteUser(String id);
}
