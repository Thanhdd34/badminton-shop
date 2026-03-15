package com.thanhdd34.badmintonshop.service.impl;

import com.thanhdd34.badmintonshop.dto.cart.AddToCartRequestDTO;
import com.thanhdd34.badmintonshop.entity.*;
import com.thanhdd34.badmintonshop.exception.ResourceNotFoundException;
import com.thanhdd34.badmintonshop.repository.CartReponsityory;
import com.thanhdd34.badmintonshop.repository.ProductRepository;
import com.thanhdd34.badmintonshop.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartReponsityory cartReponsityory;
    private final ProductRepository productRepository;


    public CartServiceImpl(CartReponsityory cartReponsityory, ProductRepository productRepository) {
        this.cartReponsityory = cartReponsityory;
        this.productRepository = productRepository;
    }

    @Override
    public void addToCart(AddToCartRequestDTO requets) {
        Product product = productRepository.findById(requets.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        if (product.getStatus() != ProductStatus.ACTIVE) {
            throw new RuntimeException("Product is not active");
        }

        // lấy user hiện tại từ security
        Long userId = 1L;

        Cart cart = cartReponsityory.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User  user = new User();
                    user.setId(userId);
                    newCart.setUser(user);
                    return cartReponsityory.save(newCart);
                });
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(requets.getQuantity());
        cartItem.setPriceAtTime(product.getPrice());

        cart.getItems().add(cartItem);
        cartReponsityory.save(cart);
    }
}
