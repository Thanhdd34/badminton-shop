package com.thanhdd34.badmintonshop.service.impl;

import com.thanhdd34.badmintonshop.dto.cart.AddToCartRequestDTO;
import com.thanhdd34.badmintonshop.dto.cart.CartItemResponseDTO;
import com.thanhdd34.badmintonshop.dto.cart.CartResponseDTO;
import com.thanhdd34.badmintonshop.entity.*;
import com.thanhdd34.badmintonshop.exception.ResourceNotFoundException;
import com.thanhdd34.badmintonshop.repository.CartItemRepository;
import com.thanhdd34.badmintonshop.repository.CartReponsityory;
import com.thanhdd34.badmintonshop.repository.ProductRepository;
import com.thanhdd34.badmintonshop.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartReponsityory cartReponsityory;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    public CartServiceImpl(CartReponsityory cartReponsityory, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartReponsityory = cartReponsityory;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addToCart(AddToCartRequestDTO requets) {
        Product product = productRepository.findById(requets.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        if (requets.getQuantity() > product.getQuantity()){
            throw new RuntimeException("not enough stock");
        }
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
//        CartItem cartItem = new CartItem();
//        cartItem.setCart(cart);
//        cartItem.setProduct(product);
//        cartItem.setQuantity(requets.getQuantity());
//        cartItem.setPriceAtTime(product.getPrice());
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + requets.getQuantity();

            if (newQuantity > product.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            existingItem.setQuantity(newQuantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(requets.getQuantity());
            cartItem.setPriceAtTime(product.getPrice());

            cart.getItems().add(cartItem);
        }

//        cart.getItems().add(cartItem);
        cartReponsityory.save(cart);
    }

    @Override
    public void removeItem(Long cartItemId) {
        Long userId = 1L;

        CartItem cartItem = cartItemRepository.findByIdAndCartUserId(cartItemId,userId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));
        cartItemRepository.delete(cartItem);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponseDTO getMyCart() {

        Long userId = 1L;

        Cart cart = cartReponsityory.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(cart.getId());

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<CartItemResponseDTO> itemDTOs = cart.getItems().stream().map(item -> {

            CartItemResponseDTO dto = new CartItemResponseDTO();

            dto.setId(item.getId());
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPriceAtTime());

            BigDecimal subtotal = item.getPriceAtTime()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            dto.setSubtotal(subtotal);

            return dto;

        }).toList();

        for (CartItemResponseDTO item : itemDTOs) {
            totalAmount = totalAmount.add(item.getSubtotal());
        }

        response.setItems(itemDTOs);
        response.setTotalAmount(totalAmount);

        return response;
    }

    @Override
    public void updateQuantity(Long cartItemId, int quantity) {
        Long userId = 1L;

        CartItem cartItem = cartItemRepository
                .findByIdAndCartUserId(cartItemId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));
        if (quantity<= 0){
            cartItemRepository.delete(cartItem);
            return;
        }

        //cartItem.setQuantity(quantity);
        Product product = cartItem.getProduct();
        if(quantity > product.getQuantity()){
            throw new RuntimeException("Not enough stock");
        }
        cartItemRepository.save(cartItem);
    }
}
