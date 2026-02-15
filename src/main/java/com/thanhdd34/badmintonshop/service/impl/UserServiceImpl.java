package com.thanhdd34.badmintonshop.service.impl;

import com.thanhdd34.badmintonshop.dto.UserCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.UserResponseDTO;
import com.thanhdd34.badmintonshop.entity.User;
import com.thanhdd34.badmintonshop.entity.enums.Role;
import com.thanhdd34.badmintonshop.repository.UserRepository;
import com.thanhdd34.badmintonshop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO userRequest){
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()

        );
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
