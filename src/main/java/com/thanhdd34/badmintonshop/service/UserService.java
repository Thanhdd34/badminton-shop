package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.dto.UserCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.UserResponseDTO;
import com.thanhdd34.badmintonshop.entity.User;

//service xu ly logic
public interface UserService {
    UserResponseDTO createUser(UserCreateRequestDTO userRequest);

    UserResponseDTO getUserById(Long id);
}

