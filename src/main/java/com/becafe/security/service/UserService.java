package com.becafe.security.service;

import com.becafe.exceptions.RegistrationException;
import com.becafe.model.User;
import com.becafe.repository.UserRepository;
import com.becafe.security.dto.AuthenticatedUserDto;
import com.becafe.security.dto.RegistrationRequest;
import com.becafe.security.dto.RegistrationResponse;
import com.becafe.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// made by Ayoub Youb with ❤️
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

	// made by Ayoub Youb with ❤️
	@Slf4j
	@Service
	@RequiredArgsConstructor
	class UserValidationService {

		private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

		private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

		private final UserRepository userRepository;

		private final ExceptionMessageAccessor exceptionMessageAccessor;

		public void validateUser(RegistrationRequest registrationRequest) {

			final String email = registrationRequest.getEmail();
			final String username = registrationRequest.getUsername();

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
