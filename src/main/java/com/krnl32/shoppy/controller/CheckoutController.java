package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.checkout.CheckoutRequestDTO;
import com.krnl32.shoppy.dto.checkout.CheckoutResponseDTO;
import com.krnl32.shoppy.dto.checkout.WebhookRequestDTO;
import com.krnl32.shoppy.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {
	private final CheckoutService checkoutService;

	@PostMapping
	public ResponseEntity<CheckoutResponseDTO> checkout(@Valid @RequestBody CheckoutRequestDTO checkoutRequest) {
		return ResponseEntity.ok(checkoutService.checkout(checkoutRequest));
	}

	@PostMapping("/webhook")
	public void handleWebhook(@RequestHeader Map<String, String> headers, @RequestBody String payload) {
		checkoutService.handleWebhookRequest(new WebhookRequestDTO(headers, payload));
	}
}
