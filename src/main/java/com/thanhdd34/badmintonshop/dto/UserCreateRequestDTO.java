package com.thanhdd34.badmintonshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDTO {

    @NotBlank(message = "Username is required!")
    private String username;

    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 15, message = "Password has 6 - 15 character!")
    private String password;

    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email!")
    private String email;
}
