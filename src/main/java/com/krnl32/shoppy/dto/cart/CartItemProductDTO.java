package com.krnl32.shoppy.dto.cart;

import lombok.Data;

@Data
public class CartItemProductDTO {
	private Long id;
	private String name;
	private Double price;
}
