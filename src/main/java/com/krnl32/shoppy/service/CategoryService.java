package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.category.CategoryCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.category.CategoryDTO;
import com.krnl32.shoppy.dto.category.CategoryPatchRequestDTO;

import java.util.List;

public interface CategoryService {
	List<CategoryDTO> findAll();
	CategoryDTO findById(Long id);
	CategoryDTO create(CategoryCreateUpdateRequestDTO categoryRequest);
	CategoryDTO update(Long id, CategoryCreateUpdateRequestDTO categoryRequest);
	CategoryDTO patch(Long id, CategoryPatchRequestDTO categoryRequest);
	void delete(Long id);
}
