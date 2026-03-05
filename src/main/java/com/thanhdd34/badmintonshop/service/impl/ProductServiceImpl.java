package com.thanhdd34.badmintonshop.service.impl;

import com.thanhdd34.badmintonshop.dto.product.ProductCreateRequestDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductResponseDTO;
import com.thanhdd34.badmintonshop.dto.product.ProductUpdateRequestDTO;
import com.thanhdd34.badmintonshop.entity.Product;
import com.thanhdd34.badmintonshop.entity.ProductStatus;
import com.thanhdd34.badmintonshop.exception.ResourceNotFoundException;
import com.thanhdd34.badmintonshop.repository.ProductRepository;
import com.thanhdd34.badmintonshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ================= CREATE =================

    @Override
    public ProductResponseDTO createProduct(ProductCreateRequestDTO request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStatus(ProductStatus.ACTIVE);
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    // ================= UPDATE =================
    // PUT style (update toàn bộ)

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStatus(request.getStatus());

        Product updated = productRepository.save(product);

        return mapToResponse(updated);
    }

    // ================= GET BY ID =================

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Public chỉ nên thấy ACTIVE product
        if (product.getStatus() != ProductStatus.ACTIVE) {
            throw new ResourceNotFoundException("Product not available");
        }

        return mapToResponse(product);
    }

    // ================= GET ALL (PUBLIC) =================

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {

        return productRepository
                .findByStatus(ProductStatus.ACTIVE, pageable)
                .map(this::mapToResponse);
    }

    // ================= DELETE (SOFT DELETE) =================

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Soft delete thay vì xóa khỏi DB
        product.setStatus(ProductStatus.INACTIVE);

        productRepository.save(product);
    }

    // ================= MAPPER =================

    private ProductResponseDTO mapToResponse(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setStatus(product.getStatus());
        dto.setCreatedAt(product.getCreatedAt());

        return dto;
    }
}