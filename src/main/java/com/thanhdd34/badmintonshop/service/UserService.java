package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.entity.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);
}

