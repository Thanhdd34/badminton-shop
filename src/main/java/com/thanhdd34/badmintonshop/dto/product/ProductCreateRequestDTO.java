//admin tạo sản phẩm
package com.thanhdd34.badmintonshop.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequestDTO {

    @NotBlank(message = "Product name is required!")
    private String name;

    private String description;

    @NotNull(message = "Price is required!")
    @Min(value = 0, message = "Price must be >= 0!")
    private BigDecimal price;

    @NotNull(message = "Stock is required!")
    @Min(value = 0, message = "Stock must be >= 0!")
    private Integer stock;
}
