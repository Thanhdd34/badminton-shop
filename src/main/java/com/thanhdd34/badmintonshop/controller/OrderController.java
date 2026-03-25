package com.thanhdd34.badmintonshop.controller;

import com.thanhdd34.badmintonshop.dto.order.CheckoutRequestDTO;
import com.thanhdd34.badmintonshop.dto.order.OrderResponseDTO;
import com.thanhdd34.badmintonshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderResponseDTO> checkout(@RequestBody CheckoutRequestDTO request) {
        return ResponseEntity.ok(orderService.checkout(request));
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