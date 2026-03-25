package com.thanhdd34.badmintonshop.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutRequestDTO {

    private String receiverName;
    private String receiverPhone;
    private String shippingAddress;
    private String note;


}
