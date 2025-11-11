package com.krnl32.shoppy.dto.order;

import com.krnl32.shoppy.entity.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
	private Long id;
	private LocalDateTime createdAt;
	private String customer;
	private PaymentStatus paymentStatus;
	private List<OrderItemDTO> items;
	private Double totalPrice;
}
