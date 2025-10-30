package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.product.ProductCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.product.ProductDTO;
import com.krnl32.shoppy.dto.product.ProductPatchRequestDTO;
import com.krnl32.shoppy.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getProducts() {
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
		return ResponseEntity.ok(productService.findById(productId));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductCreateUpdateRequestDTO productRequest, UriComponentsBuilder uriBuilder) {
		ProductDTO productDTO = productService.create(productRequest);
		URI uri = uriBuilder.path("/products/{productId}").buildAndExpand(productDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(productDTO);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductCreateUpdateRequestDTO productRequest) {
		return ResponseEntity.ok(productService.update(productId, productRequest));
	}

	@PatchMapping("/{productId}")
	public ResponseEntity<ProductDTO> patchProduct(@PathVariable Long productId, @Valid @RequestBody ProductPatchRequestDTO productRequest) {
		return ResponseEntity.ok(productService.patch(productId, productRequest));
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.delete(productId);
		return ResponseEntity.noContent().build();
	}
}
