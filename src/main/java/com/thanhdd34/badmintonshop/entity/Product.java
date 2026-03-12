package com.thanhdd34.badmintonshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tên sản phẩm
    @Column(nullable = false)
    private String name;

    // Mô tả chi tiết
    @Column(columnDefinition = "TEXT")
    private String description;

    // Giá tiền (dùng BigDecimal, KHÔNG dùng double)
    @Column(nullable = false)
    private BigDecimal price;

    // Số lượng tồn kho
    @Column(nullable = false)
    private Integer stock;

    // Ảnh sản phẩm
    private String imageUrl;

    // Trạng thái sản phẩm
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    // Ngày tạo
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Ngày cập nhật
    private LocalDateTime updatedAt;

    // ===== Lifecycle =====
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = ProductStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

