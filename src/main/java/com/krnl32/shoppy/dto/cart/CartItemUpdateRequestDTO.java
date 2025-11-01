package com.krnl32.shoppy.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemUpdateRequestDTO {
	@NotNull(message = "Quantity Required")
	@Min(value = 1, message = "Quantity must be Greater than 0")
	private Integer quantity;
}
