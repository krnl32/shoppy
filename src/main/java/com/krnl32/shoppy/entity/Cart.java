package com.krnl32.shoppy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;

	@Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
	private Set<CartItem> items = new LinkedHashSet<>();

	public Double getTotalPrice() {
		return items.stream()
			.mapToDouble(CartItem::getTotalPrice)
			.sum();
	}

	public CartItem getItem(Long productId) {
		return items.stream()
			.filter(item -> item.getProduct().getId().equals(productId))
			.findFirst()
			.orElse(null);
	}

	public CartItem addItem(Product product, Integer quantity) {
		CartItem cartItem = getItem(product.getId());

		if (cartItem != null) {
			Integer quant = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(quant);
		} else {
			cartItem = new CartItem(null, this, product, quantity);
			items.add(cartItem);
		}

		return cartItem;
	}

	public void removeItem(Long productId) {
		CartItem cartItem = getItem(productId);
		if (cartItem != null) {
			items.remove(cartItem);
			cartItem.setCart(null);
		}
	}
}
