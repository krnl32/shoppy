package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.category.CategoryCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.category.CategoryDTO;
import com.krnl32.shoppy.dto.category.CategoryPatchRequestDTO;
import com.krnl32.shoppy.entity.Category;
import com.krnl32.shoppy.exception.CategoryNotFoundException;
import com.krnl32.shoppy.mapper.CategoryMapper;
import com.krnl32.shoppy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll()
			.stream().map(categoryMapper::toDTO)
			.toList();
	}

	@Override
	public CategoryDTO findById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category ID Not Found: " + id);
		}

		return categoryMapper.toDTO(category.get());
	}

	@Override
	public CategoryDTO create(CategoryCreateUpdateRequestDTO categoryRequest) {
		Category category = categoryMapper.toEntity(categoryRequest);
		Category savedCategory = categoryRepository.save(category);
		return categoryMapper.toDTO(savedCategory);
	}

	@Override
	@Transactional
	public CategoryDTO update(Long id, CategoryCreateUpdateRequestDTO categoryRequest) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category ID Not Found: " + id);
		}

		categoryMapper.update(categoryRequest, category.get());
		return categoryMapper.toDTO(category.get());
	}

	@Override
	@Transactional
	public CategoryDTO patch(Long id, CategoryPatchRequestDTO categoryRequest) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category ID Not Found: " + id);
		}

		categoryMapper.patch(categoryRequest, category.get());
		return categoryMapper.toDTO(category.get());
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category ID Not Found: " + id);
		}

		category.get().getProducts().forEach(product -> product.setCategory(null));
		categoryRepository.delete(category.get());
	}
}
