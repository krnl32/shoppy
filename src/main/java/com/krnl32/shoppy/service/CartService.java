package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.cart.CartDTO;
import com.krnl32.shoppy.dto.cart.CartItemCreateRequestDTO;
import com.krnl32.shoppy.dto.cart.CartItemDTO;
import com.krnl32.shoppy.dto.cart.CartItemUpdateRequestDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {
	List<CartDTO> findAll();
	CartDTO findById(UUID id);
	CartDTO create();
	CartItemDTO createItem(UUID id, CartItemCreateRequestDTO cartItemRequest);
	CartItemDTO updateItem(UUID id, Long productId, CartItemUpdateRequestDTO cartItemRequest);
	void deleteCartItem(UUID id, Long productId);
	void clearCart(UUID id);
}
