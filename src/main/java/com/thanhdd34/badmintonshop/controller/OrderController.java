package com.thanhdd34.badmintonshop.controller;

import com.thanhdd34.badmintonshop.dto.order.CheckoutRequestDTO;
import com.thanhdd34.badmintonshop.dto.order.OrderResponseDTO;
import com.thanhdd34.badmintonshop.entity.Order;
import com.thanhdd34.badmintonshop.service.OrderService;
import com.thanhdd34.badmintonshop.service.VNPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    public final VNPayService vnPayService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequestDTO request) {

        Order order = orderService.checkout(request);

        String paymentUrl = vnPayService.createPaymentUrl(order);

        return ResponseEntity.ok(paymentUrl);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getMyOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getMyOrderById(orderId));
    }
}