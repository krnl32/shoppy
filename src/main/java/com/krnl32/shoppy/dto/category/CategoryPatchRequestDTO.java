package com.krnl32.shoppy.dto.category;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryPatchRequestDTO {
	@Size(min = 3, max = 15, message = "Category name must be between 3-15 Characters")
	private String name;

	@Size(min = 5, max = 32, message = "Category description must be between 5-32 Characters")
	private String description;
}
