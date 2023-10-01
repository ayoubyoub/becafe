package com.becafe.serviceImpl;

import com.becafe.dto.UserDto;
import com.becafe.model.Costumer;
import com.becafe.model.Role;
import com.becafe.model.Seller;
import com.becafe.model.User;
import com.becafe.repository.CostumerRepository;
import com.becafe.repository.SellerRepository;
import com.becafe.repository.UserRepository;
import com.becafe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CostumerRepository costumerRepository;

    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           CostumerRepository costumerRepository,
                           SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.costumerRepository = costumerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> modelMapper.map(value, UserDto.class));
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setUserID(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ADMIN);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto saveCostumer(UserDto userDto) {
        Costumer costumer = modelMapper.map(userDto, Costumer.class);
        costumer.setUserID(UUID.randomUUID().toString());
        costumer.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        costumer.setRole(Role.COSTUMER);
        costumer = costumerRepository.save(costumer);
        return modelMapper.map(costumer, UserDto.class);
    }

    @Override
    public UserDto saveSeller(UserDto userDto) {
        Seller seller = modelMapper.map(userDto, Seller.class);
        seller.setUserID(UUID.randomUUID().toString());
        seller.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        seller.setRole(Role.SELLER);
        seller = sellerRepository.save(seller);
        return modelMapper.map(seller, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}