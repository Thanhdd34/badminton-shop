package com.thanhdd34.badmintonshop.dto.order;

import com.thanhdd34.badmintonshop.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {
    private Long orderId;
    private String orderCode;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private String receiverName;
    private String receiverPhone;
    private String shippingAddress;
    private String note;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDTO> items;

}
