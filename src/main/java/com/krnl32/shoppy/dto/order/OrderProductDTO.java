package com.krnl32.shoppy.dto.order;

import lombok.Data;

@Data
public class OrderProductDTO {
	private Long id;
	private String name;
	private Double price;
}
