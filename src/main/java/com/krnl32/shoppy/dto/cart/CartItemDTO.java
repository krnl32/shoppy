package com.krnl32.shoppy.dto.cart;

import lombok.Data;

@Data
public class CartItemDTO {
	private CartItemProductDTO product;
	private Integer quantity;
	private Double totalPrice;
}
