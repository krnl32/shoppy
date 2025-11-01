package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.cart.CartDTO;
import com.krnl32.shoppy.dto.cart.CartItemCreateRequestDTO;
import com.krnl32.shoppy.dto.cart.CartItemDTO;
import com.krnl32.shoppy.dto.cart.CartItemUpdateRequestDTO;
import com.krnl32.shoppy.entity.Cart;
import com.krnl32.shoppy.entity.CartItem;
import com.krnl32.shoppy.entity.Product;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import com.krnl32.shoppy.mapper.CartMapper;
import com.krnl32.shoppy.repository.CartRepository;
import com.krnl32.shoppy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final CartMapper cartMapper;

	@Override
	public List<CartDTO> findAll() {
		return cartRepository.findAll()
			.stream().map(cartMapper::toDTO)
			.toList();
	}

	@Override
	public CartDTO findById(UUID id) {
		Optional<Cart> cart = cartRepository.findById(id);
		if (cart.isEmpty()) {
			throw new ResourceNotFoundException("Cart ID Not Found: " + id);
		}

		return cartMapper.toDTO(cart.get());
	}

	@Override
	public CartDTO create() {
		Cart cart = cartRepository.save(new Cart());
		return cartMapper.toDTO(cart);
	}

	@Override
	public CartItemDTO createItem(UUID id, CartItemCreateRequestDTO cartItemRequest) {
		Optional<Cart> cart = cartRepository.findById(id);
		if (cart.isEmpty()) {
			throw new ResourceNotFoundException("Cart ID Not Found: " + id);
		}

		Optional<Product> product = productRepository.findById(cartItemRequest.getProductId());
		if (product.isEmpty()) {
			throw new ResourceNotFoundException("Product ID Not Found: " + id);
		}

		CartItem item = cart.get().addItem(product.get(), cartItemRequest.getQuantity());
		cartRepository.save(cart.get());
		return cartMapper.toDTO(item);
	}

	@Override
	public CartItemDTO updateItem(UUID id, Long productId, CartItemUpdateRequestDTO cartItemRequest) {
		Optional<Cart> cart = cartRepository.findById(id);
		if (cart.isEmpty()) {
			throw new ResourceNotFoundException("Cart ID Not Found: " + id);
		}

		CartItem cartItem = cart.get().getItem(productId);
		if (cartItem == null) {
			throw new ResourceNotFoundException("Product ID Not Found: " + id);
		}

		cartItem.setQuantity(cartItemRequest.getQuantity());
		cartRepository.save(cart.get());
		return cartMapper.toDTO(cartItem);
	}

	@Override
	public void deleteCartItem(UUID id, Long productId) {
		Optional<Cart> cart = cartRepository.findById(id);
		if (cart.isEmpty()) {
			throw new ResourceNotFoundException("Cart ID Not Found: " + id);
		}

		cart.get().removeItem(productId);
		cartRepository.save(cart.get());
	}

	@Override
	public void clearCart(UUID id) {
		Optional<Cart> cart = cartRepository.findById(id);
		if (cart.isEmpty()) {
			throw new ResourceNotFoundException("Cart ID Not Found: " + id);
		}

		cart.get().getItems().clear();
		cartRepository.save(cart.get());
	}
}
