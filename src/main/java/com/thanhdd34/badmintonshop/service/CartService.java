package com.thanhdd34.badmintonshop.service;


import com.thanhdd34.badmintonshop.dto.cart.AddToCartRequestDTO;
import com.thanhdd34.badmintonshop.dto.cart.CartResponseDTO;

public interface CartService {
    void addToCart(AddToCartRequestDTO requets);
    void removeItem(Long cartItemId);
    CartResponseDTO getMyCart();
    void updateQuantity(Long cartItemId, int quantity);
}
