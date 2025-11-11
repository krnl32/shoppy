package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.order.OrderDTO;
import com.krnl32.shoppy.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> getOrders() {
		return ResponseEntity.ok(orderService.findAll());
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
		return ResponseEntity.ok(orderService.findById(orderId));
	}
}
