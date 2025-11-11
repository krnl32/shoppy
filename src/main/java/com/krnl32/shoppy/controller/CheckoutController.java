package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.checkout.CheckoutRequestDTO;
import com.krnl32.shoppy.dto.checkout.CheckoutResponseDTO;
import com.krnl32.shoppy.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {
	private final CheckoutService checkoutService;

	@PostMapping
	public ResponseEntity<CheckoutResponseDTO> checkout(@Valid @RequestBody CheckoutRequestDTO checkoutRequest) {
		return ResponseEntity.ok(checkoutService.checkout(checkoutRequest));
	}
}
