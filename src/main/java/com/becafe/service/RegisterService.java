package com.becafe.service;

import com.becafe.dto.UserDto;
import com.becafe.exceptions.RegistrationException;
import com.becafe.repository.UserRepository;
import com.becafe.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// made by Ayoub Youb with ❤️
public interface RegisterService {
    List<UserDto> getAllUsers();
    Optional<UserDto> findById(String id);
    Optional<UserDto> findByUsername(String username);
    UserDto saveUser(UserDto userDto);
    void deleteUser(String id);
    @Slf4j
    @Service
    @RequiredArgsConstructor
    class UserValidationService {

        private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

        private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

        private final UserRepository userRepository;

        private final ExceptionMessageAccessor exceptionMessageAccessor;

        public void validateUser(UserDto userDto) {

            final String email = userDto.getEmail();
            final String username = userDto.getUsername();

            checkEmail(email);
            checkUsername(username);
        }

        private void checkUsername(String username) {

            final boolean existsByUsername = userRepository.existsByUsername(username);

            if (existsByUsername) {

                log.warn("{} is already being used!", username);

                final String existsUsername = exceptionMessageAccessor.getMessage(null, USERNAME_ALREADY_EXISTS);
                throw new RegistrationException(existsUsername);
            }

        }

        private void checkEmail(String email) {

            final boolean existsByEmail = userRepository.existsByEmail(email);

            if (existsByEmail) {

                log.warn("{} is already being used!", email);

                final String existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
                throw new RegistrationException(existsEmail);
            }
        }

    }
}
