package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.dto.product.ProductCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductResponseDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreateRequestDTO request);

    ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO request);

    ProductResponseDTO getProductById(Long id);

    Page<ProductResponseDTO> getAllProducts(Pageable pageable);

    Page<ProductResponseDTO> searchProducts(String keyword, Pageable pageable);

    Page<ProductResponseDTO> getProductsByCategory(Long categoryId,Pageable pageable);

    void deleteProduct(Long id);
}