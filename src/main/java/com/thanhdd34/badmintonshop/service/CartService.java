package com.thanhdd34.badmintonshop.service;


import com.thanhdd34.badmintonshop.dto.cart.AddToCartRequestDTO;

public interface CartService {
    void addToCart(AddToCartRequestDTO requets);
}
