package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {
	List<OrderDTO> findAll();
	OrderDTO findById(Long id);
}
