package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.dto.order.CheckoutRequestDTO;
import com.thanhdd34.badmintonshop.dto.order.OrderResponseDTO;
import com.thanhdd34.badmintonshop.entity.Order;

import java.util.List;

public interface OrderService {

    Order checkout(CheckoutRequestDTO checkoutRequestDTO);

    List<OrderResponseDTO> getMyOrders();

    OrderResponseDTO getMyOrderById(Long orderId);


}
