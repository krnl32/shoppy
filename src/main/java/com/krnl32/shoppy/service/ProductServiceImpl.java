package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.product.ProductCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.product.ProductDTO;
import com.krnl32.shoppy.dto.product.ProductPatchRequestDTO;
import com.krnl32.shoppy.entity.Category;
import com.krnl32.shoppy.entity.Product;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import com.krnl32.shoppy.mapper.ProductMapper;
import com.krnl32.shoppy.repository.CategoryRepository;
import com.krnl32.shoppy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductMapper productMapper;

	@Override
	public List<ProductDTO> findAll() {
		return productRepository.findAll()
			.stream().map(productMapper::toDTO)
			.toList();
	}

	@Override
	public ProductDTO findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new ResourceNotFoundException("Product ID Not Found: " + id);
		}

		return productMapper.toDTO(product.get());
	}

	@Override
	public ProductDTO create(ProductCreateUpdateRequestDTO productRequest) {
		Optional<Category> category = categoryRepository.findById(productRequest.getCategoryId());
		if (category.isEmpty()) {
			throw new ResourceNotFoundException("Category ID Not Found: " + productRequest.getCategoryId());
		}

		Product product = productMapper.toEntity(productRequest);
		product.setCategory(category.get());

		Product savedProduct = productRepository.save(product);
		return productMapper.toDTO(savedProduct);
	}

	@Override
	public ProductDTO update(Long id, ProductCreateUpdateRequestDTO productRequest) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new ResourceNotFoundException("Product ID Not Found: " + id);
		}

		Optional<Category> category = categoryRepository.findById(productRequest.getCategoryId());
		if (category.isEmpty()) {
			throw new ResourceNotFoundException("Category ID Not Found: " + productRequest.getCategoryId());
		}

		productMapper.update(productRequest, product.get());
		product.get().setCategory(category.get());

		Product updatedProduct = productRepository.save(product.get());
		return productMapper.toDTO(updatedProduct);
	}

	@Override
	public ProductDTO patch(Long id, ProductPatchRequestDTO productRequest) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new ResourceNotFoundException("Product ID Not Found: " + id);
		}

		if (productRequest.getCategoryId() != null) {
			Optional<Category> category = categoryRepository.findById(productRequest.getCategoryId());

			if (category.isEmpty()) {
				throw new ResourceNotFoundException("Category ID Not Found: " + productRequest.getCategoryId());
			}

			product.get().setCategory(category.get());
		}

		productMapper.patch(productRequest, product.get());

		Product patchedProduct = productRepository.save(product.get());
		return productMapper.toDTO(patchedProduct);
	}

	@Override
	public void delete(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new ResourceNotFoundException("Product ID Not Found: " + id);
		}

		productRepository.delete(product.get());
	}
}
