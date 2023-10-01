package com.becafe.controller;

import com.becafe.dto.UserDto;
import com.becafe.service.RegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;
    private final ModelMapper modelMapper;

    @Autowired
    public RegisterController(RegisterService registerService, ModelMapper modelMapper) {
        this.registerService = registerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = registerService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDto> findById(@PathVariable String userID) {
        Optional<UserDto> user = registerService.findById(userID);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = registerService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userID, @Valid @RequestBody UserDto updatedUser) {
        Optional<UserDto> existingUserOptional = registerService.findById(userID);

        if (existingUserOptional.isPresent()) {
            UserDto existingUserEntity = existingUserOptional.get();
            modelMapper.map(updatedUser, existingUserEntity);
            UserDto updatedUserEntity = registerService.saveUser(existingUserEntity);
            return ResponseEntity.ok(updatedUserEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userID) {
        Optional<UserDto> user = registerService.findById(userID);
        if (user.isPresent()) {
            registerService.deleteUser(userID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
