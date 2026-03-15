package com.thanhdd34.badmintonshop.controller;

import com.thanhdd34.badmintonshop.dto.cart.AddToCartRequestDTO;
import com.thanhdd34.badmintonshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequestDTO requets){
        cartService.addToCart(requets);
        return ResponseEntity.ok("Add cart successfully");
    }
}
