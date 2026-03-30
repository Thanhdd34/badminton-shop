package com.thanhdd34.badmintonshop.service.impl;

import com.thanhdd34.badmintonshop.dto.order.CheckoutRequestDTO;
import com.thanhdd34.badmintonshop.dto.order.OrderItemResponseDTO;
import com.thanhdd34.badmintonshop.dto.order.OrderResponseDTO;
import com.thanhdd34.badmintonshop.entity.*;
import com.thanhdd34.badmintonshop.exception.ResourceNotFoundException;
import com.thanhdd34.badmintonshop.repository.CartReponsityory;
import com.thanhdd34.badmintonshop.repository.OrderRepository;
import com.thanhdd34.badmintonshop.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CartReponsityory cartRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartReponsityory cartRepository,
                            OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order checkout(CheckoutRequestDTO request) {
        Long userId = 1L;

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setOrderCode("ORD-" + UUID.randomUUID().toString().substring(0, 8));

        User user = new User();
        user.setId(userId);
        order.setUser(user);

        order.setReceiverName(request.getReceiverName());
        order.setReceiverPhone(request.getReceiverPhone());
        order.setShippingAddress(request.getShippingAddress());
        order.setNote(request.getNote());
        order.setOrderStatus(OrderStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            if (product.getStatus() != ProductStatus.ACTIVE) {
                throw new RuntimeException("Product is not available: " + product.getName());
            }

            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtTime(product.getPrice());

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            orderItem.setSubtotal(subtotal);

            order.getItems().add(orderItem);

            totalAmount = totalAmount.add(subtotal);

            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();

        return savedOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getMyOrders() {
        Long userId = 1L;

        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO getMyOrderById(Long orderId) {
        Long userId = 1L;

        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return mapToResponse(order);
    }

    private OrderResponseDTO mapToResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setOrderCode(order.getOrderCode());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setReceiverName(order.getReceiverName());
        dto.setReceiverPhone(order.getReceiverPhone());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setNote(order.getNote());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemResponseDTO itemDTO = new OrderItemResponseDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPriceAtTime(item.getPriceAtTime());
            itemDTO.setSubtotal(item.getSubtotal());
            return itemDTO;
        }).toList();

        dto.setItems(itemDTOs);

        return dto;
    }

}