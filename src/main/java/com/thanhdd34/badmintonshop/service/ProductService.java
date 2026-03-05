package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.dto.product.ProductCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductResponseDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreateRequestDTO request);

    ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO request);

    void deleteProduct(Long id);

    Page<ProductResponseDTO> getAllProducts(Pageable pageable);

    ProductResponseDTO getProductById(Long id);
}
