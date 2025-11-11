package com.krnl32.shoppy.repository;

import com.krnl32.shoppy.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
