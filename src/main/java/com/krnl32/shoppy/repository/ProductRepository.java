package com.krnl32.shoppy.repository;

import com.krnl32.shoppy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
