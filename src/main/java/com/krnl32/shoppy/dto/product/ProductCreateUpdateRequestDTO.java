package com.krnl32.shoppy.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductCreateUpdateRequestDTO {
	@NotBlank(message = "Product Name Required")
	@Size(min = 3, max = 15, message = "Product name must be between 3-15 Characters")
	private String name;

	@NotBlank(message = "Product Description Required")
	@Size(min = 5, max = 32, message = "Product description must be between 5-32 Characters")
	private String description;

	@NotNull(message = "Product Price Required")
	@PositiveOrZero(message = "Price Cannot be Negative")
	private Double price;

	@NotNull(message = "Product Stock Required")
	@PositiveOrZero(message = "Stock Count Cannot be Negative")
	private Integer stock;

	@NotNull(message = "Product Category ID Required")
	private Long categoryId;
}
