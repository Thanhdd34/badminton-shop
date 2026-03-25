package com.thanhdd34.badmintonshop.repository;

import com.thanhdd34.badmintonshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
