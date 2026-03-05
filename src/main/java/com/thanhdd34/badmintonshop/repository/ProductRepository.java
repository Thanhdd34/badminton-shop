package com.thanhdd34.badmintonshop.repository;

import com.thanhdd34.badmintonshop.entity.Product;
import com.thanhdd34.badmintonshop.entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //lấy tất cả sản phẩm theo trạng thái
    List<Product> findByStatus(ProductStatus status);

    //Kiểm tra tên sản phẩm đã tồn tại chưa
    boolean existsByName(String name);

    Page<Product> findByStatus(ProductStatus status, Pageable pageable);
}
