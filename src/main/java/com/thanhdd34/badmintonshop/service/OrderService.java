package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.dto.order.CheckoutRequestDTO;
import com.thanhdd34.badmintonshop.dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO);

    List<OrderResponseDTO> getMyOrders();

    OrderResponseDTO getMyOrderById(Long orderId);
}
