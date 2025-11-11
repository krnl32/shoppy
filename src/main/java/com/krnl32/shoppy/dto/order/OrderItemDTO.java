package com.krnl32.shoppy.dto.order;

import lombok.Data;

@Data
public class OrderItemDTO {
	private OrderProductDTO product;
	private Integer quantity;
	private Double totalPrice;
}
