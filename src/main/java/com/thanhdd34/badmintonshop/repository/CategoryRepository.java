package com.thanhdd34.badmintonshop.repository;

import com.thanhdd34.badmintonshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}