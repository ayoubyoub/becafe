package com.becafe.security.jwt;

import com.becafe.dto.UserDto;
import com.becafe.model.User;
import com.becafe.security.dto.LoginRequest;
import com.becafe.security.dto.LoginResponse;
import com.becafe.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

// made by Ayoub Youb with ❤️
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final RegisterService registerService;

	private final JwtTokenManager jwtTokenManager;

	private final AuthenticationManager authenticationManager;

	private final ModelMapper modelMapper;

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final Optional<UserDto> authenticatedUserDto = registerService.findByUsername(username);

		final User user = modelMapper.map(authenticatedUserDto.get(), User.class);
		final String token = jwtTokenManager.generateToken(user);

		log.info("{} has successfully logged in!", user.getUsername());

		return new LoginResponse(token);
	}

}
