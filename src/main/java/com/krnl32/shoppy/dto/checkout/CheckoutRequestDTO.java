package com.krnl32.shoppy.dto.checkout;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CheckoutRequestDTO {
	@NotNull(message = "Cart ID Required")
	private UUID cartId;
}
