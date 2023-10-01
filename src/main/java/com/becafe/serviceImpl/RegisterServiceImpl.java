package com.becafe.serviceImpl;

import com.becafe.dto.UserDto;
import com.becafe.model.Costumer;
import com.becafe.model.Seller;
import com.becafe.model.User;
import com.becafe.repository.CostumerRepository;
import com.becafe.repository.SellerRepository;
import com.becafe.repository.UserRepository;
import com.becafe.service.RegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final CostumerRepository costumerRepository;
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserValidationService userValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository,
                               ModelMapper modelMapper,
                               BCryptPasswordEncoder bCryptPasswordEncoder,
                               CostumerRepository costumerRepository,
                               SellerRepository sellerRepository,
                               UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.costumerRepository = costumerRepository;
        this.sellerRepository = sellerRepository;
        this.userValidationService = userValidationService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> modelMapper.map(value, UserDto.class));
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> modelMapper.map(value, UserDto.class));
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        userValidationService.validateUser(userDto);

        User user;
        switch (userDto.getRole()) {
            case ADMIN:
                user = modelMapper.map(userDto, User.class);
                break;
            case COSTUMER:
                user = modelMapper.map(userDto, Costumer.class);
                break;
            case SELLER:
                user = modelMapper.map(userDto, Seller.class);
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }

        user.setUserID(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        user = userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
