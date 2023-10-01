package com.becafe.security.service;

import com.becafe.dto.UserDto;
import com.becafe.model.Role;
import com.becafe.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

// made by Ayoub Youb with ❤️
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	private final RegisterService registerService;

	private final ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) {

		final Optional<UserDto> authenticatedUser = registerService.findByUsername(username);

		if (Objects.isNull(authenticatedUser)) {
			throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
		}

		final String authenticatedUsername = authenticatedUser.get().getUsername();
		final String authenticatedPassword = authenticatedUser.get().getPassword();
		final Role userRole = authenticatedUser.get().getRole();
		final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.name());

		return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
	}
}
