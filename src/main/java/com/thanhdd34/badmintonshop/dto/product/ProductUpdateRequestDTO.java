//admin cap nhat san pham
package com.thanhdd34.badmintonshop.dto.product;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateRequestDTO {

    private String name;
    private String description;

    @Min(value = 0, message = "Price must be >= 0!")
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be >= 0!")
    private Integer stock;
}
