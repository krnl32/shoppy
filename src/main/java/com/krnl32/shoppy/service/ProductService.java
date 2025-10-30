package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.product.ProductCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.product.ProductDTO;
import com.krnl32.shoppy.dto.product.ProductPatchRequestDTO;

import java.util.List;

public interface ProductService {
	List<ProductDTO> findAll();
	ProductDTO findById(Long id);
	ProductDTO create(ProductCreateUpdateRequestDTO productRequest);
	ProductDTO update(Long id, ProductCreateUpdateRequestDTO productRequest);
	ProductDTO patch(Long id, ProductPatchRequestDTO productRequest);
	void delete(Long id);
}
