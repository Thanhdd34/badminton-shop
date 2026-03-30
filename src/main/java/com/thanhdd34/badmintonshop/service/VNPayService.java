package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.entity.Order;

public interface VNPayService {
    String createPaymentUrl(Order order);
}
