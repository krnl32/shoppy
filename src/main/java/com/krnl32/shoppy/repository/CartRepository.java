package com.krnl32.shoppy.repository;

import com.krnl32.shoppy.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findById(UUID id);
}
