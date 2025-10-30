package com.krnl32.shoppy.dto.product;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductPatchRequestDTO {
	@Size(min = 3, max = 15, message = "Product name must be between 3-15 Characters")
	private String name;

	@Size(min = 5, max = 32, message = "Product description must be between 5-32 Characters")
	private String description;

	@PositiveOrZero(message = "Price Cannot be Negative")
	private Double price;

	@PositiveOrZero(message = "Stock Count Cannot be Negative")
	private Integer stock;

	private Long categoryId;
}
