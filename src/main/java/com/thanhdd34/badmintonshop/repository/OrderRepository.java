package com.thanhdd34.badmintonshop.repository;

import com.thanhdd34.badmintonshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository  extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndUserId(Long orderId, Long userId);

    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);

}
