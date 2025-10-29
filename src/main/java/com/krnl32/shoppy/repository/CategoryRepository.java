package com.krnl32.shoppy.repository;

import com.krnl32.shoppy.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
