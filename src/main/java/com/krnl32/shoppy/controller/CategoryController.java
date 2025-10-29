package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.category.CategoryCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.category.CategoryDTO;
import com.krnl32.shoppy.dto.category.CategoryPatchRequestDTO;
import com.krnl32.shoppy.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId) {
		return ResponseEntity.ok(categoryService.findById(categoryId));
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryCreateUpdateRequestDTO categoryRequest, UriComponentsBuilder uriBuilder) {
		CategoryDTO categoryDTO = categoryService.create(categoryRequest);
		URI uri = uriBuilder.path("/categories/{categoryId}").buildAndExpand(categoryDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(categoryDTO);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryCreateUpdateRequestDTO categoryRequest) {
		return ResponseEntity.ok(categoryService.update(categoryId, categoryRequest));
	}

	@PatchMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> patchCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryPatchRequestDTO categoryRequest) {
		return ResponseEntity.ok(categoryService.patch(categoryId, categoryRequest));
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
		categoryService.delete(categoryId);
		return ResponseEntity.noContent().build();
	}
}
