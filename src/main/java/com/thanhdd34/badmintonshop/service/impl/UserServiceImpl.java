package com.thanhdd34.badmintonshop.service.impl;

import com.thanhdd34.badmintonshop.dto.UserCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.UserResponseDTO;
import com.thanhdd34.badmintonshop.entity.Role;
import com.thanhdd34.badmintonshop.entity.User;
import com.thanhdd34.badmintonshop.repository.RoleRepository;
import com.thanhdd34.badmintonshop.repository.UserRepository;
import com.thanhdd34.badmintonshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleReponsitory;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleReponsitory){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleReponsitory = roleReponsitory;
    };


    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO userRequest){

        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Role userRole = roleReponsitory.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found!"));
        user.setRoles(Set.of(userRole));

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
