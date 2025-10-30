package com.krnl32.shoppy.dto.product;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private int stock;
	private Long categoryId;
}
