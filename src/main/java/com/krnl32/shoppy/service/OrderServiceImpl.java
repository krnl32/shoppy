package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.order.OrderDTO;
import com.krnl32.shoppy.entity.Order;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import com.krnl32.shoppy.mapper.OrderMapper;
import com.krnl32.shoppy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;

	@Override
	public List<OrderDTO> findAll() {
		return orderRepository.findAll()
			.stream().map(orderMapper::toDTO)
			.toList();
	}

	@Override
	public OrderDTO findById(Long id) {
		Order order = orderRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Order ID Not Found: " + id));

		return orderMapper.toDTO(order);
	}
}
