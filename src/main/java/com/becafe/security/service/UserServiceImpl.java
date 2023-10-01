package com.becafe.security.service;

import com.becafe.model.Costumer;
import com.becafe.model.Role;
import com.becafe.model.Seller;
import com.becafe.model.User;
import com.becafe.repository.CostumerRepository;
import com.becafe.repository.SellerRepository;
import com.becafe.security.dto.AuthenticatedUserDto;
import com.becafe.security.dto.RegistrationRequest;
import com.becafe.security.dto.RegistrationResponse;
import com.becafe.security.mapper.CostumerMapper;
import com.becafe.security.mapper.SellerMapper;
import com.becafe.security.mapper.UserMapper;
import com.becafe.utils.GeneralMessageAccessor;
import com.becafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

// made by Ayoub Youb with ❤️
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;

	private final CostumerRepository costumerRepository;

	private final SellerRepository sellerRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest, Role role) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setUserID(UUID.randomUUID().toString());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(role);
		userRepository.save(user);

		if (role == Role.COSTUMER) {
			final Costumer costumer = CostumerMapper.INSTANCE.convertToCostumer(registrationRequest);
			costumer.setCustomerID(user.getUserID());
			costumerRepository.save(costumer);
		}
		if (role == Role.SELLER) {
			final Seller seller = SellerMapper.INSTANCE.convertToSeller(registrationRequest);
			seller.setSellerID(user.getUserID());
			sellerRepository.save(seller);
		}

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}
