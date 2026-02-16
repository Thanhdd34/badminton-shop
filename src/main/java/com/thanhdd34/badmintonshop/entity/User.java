package com.thanhdd34.badmintonshop.entity;

import com.thanhdd34.badmintonshop.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//Entity la class Java đại diện cho 1 bảng trong database
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreateAt(){
        this.createdAt = LocalDateTime.now();
    }
}

