package com.krnl32.shoppy.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateUpdateRequestDTO {
	@NotBlank(message = "Category Name Required")
	@Size(min = 3, max = 15, message = "Category name must be between 3-15 Characters")
	private String name;

	@NotBlank(message = "Category Description Required")
	@Size(min = 5, max = 32, message = "Category description must be between 5-32 Characters")
	private String description;
}
