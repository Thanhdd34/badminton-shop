package com.thanhdd34.badmintonshop.service.impl;

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
    public User createUser(User user){
        //Check user ton tai
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new RuntimeException("Username already exists!");
        });

        //set role mac dinh
        user.setRole(Role.USER);

        //save user
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
