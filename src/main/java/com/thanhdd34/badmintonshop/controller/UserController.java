package com.thanhdd34.badmintonshop.controller;


import com.thanhdd34.badmintonshop.dto.UserCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.UserResponseDTO;
import com.thanhdd34.badmintonshop.entity.User;
import com.thanhdd34.badmintonshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create user
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateRequestDTO userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    //get user
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
