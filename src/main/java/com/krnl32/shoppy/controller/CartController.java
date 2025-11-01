package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.cart.CartDTO;
import com.krnl32.shoppy.dto.cart.CartItemCreateRequestDTO;
import com.krnl32.shoppy.dto.cart.CartItemDTO;
import com.krnl32.shoppy.dto.cart.CartItemUpdateRequestDTO;
import com.krnl32.shoppy.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;

	@GetMapping
	public ResponseEntity<List<CartDTO>> getCarts() {
		return ResponseEntity.ok(cartService.findAll());
	}

	@GetMapping("/{cartId}")
	public ResponseEntity<CartDTO> getCart(@PathVariable UUID cartId) {
		return ResponseEntity.ok(cartService.findById(cartId));
	}

	@PostMapping
	public ResponseEntity<CartDTO> createCart(UriComponentsBuilder uriBuilder) {
		CartDTO cartDTO = cartService.create();
		URI uri = uriBuilder.path("/api/carts/{cartId}").buildAndExpand(cartDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(cartDTO);
	}

	@PostMapping("/{cartId}/items")
	public ResponseEntity<CartItemDTO> createCartItem(@PathVariable UUID cartId, @Valid @RequestBody CartItemCreateRequestDTO cartItemRequest) {
		return ResponseEntity.ok(cartService.createItem(cartId, cartItemRequest));
	}

	@PutMapping("/{cartId}/items/{productId}")
	public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable UUID cartId, @PathVariable Long productId, @Valid @RequestBody CartItemUpdateRequestDTO cartItemRequest) {
		return ResponseEntity.ok(cartService.updateItem(cartId, productId, cartItemRequest));
	}

	@DeleteMapping("/{cartId}/items/{productId}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable UUID cartId, @PathVariable Long productId) {
		cartService.deleteCartItem(cartId, productId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{cartId}/items")
	public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
		cartService.clearCart(cartId);
		return ResponseEntity.noContent().build();
	}
}
