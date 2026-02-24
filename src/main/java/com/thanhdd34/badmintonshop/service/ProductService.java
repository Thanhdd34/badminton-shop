package com.thanhdd34.badmintonshop.service;

import com.thanhdd34.badmintonshop.dto.product.ProductCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductResponseDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductUpdateRequestDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreateRequestDTO request);

    ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO request);

    void deleteProduct(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

}
