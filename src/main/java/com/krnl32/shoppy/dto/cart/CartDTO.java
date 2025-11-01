package com.krnl32.shoppy.dto.cart;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CartDTO {
	private UUID id;
	private Set<CartItemDTO> items;
	private Double totalPrice;
}
