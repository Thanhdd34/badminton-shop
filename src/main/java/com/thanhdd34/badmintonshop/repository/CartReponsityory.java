package com.thanhdd34.badmintonshop.repository;


import com.thanhdd34.badmintonshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartReponsityory extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
