package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.checkout.CheckoutRequestDTO;
import com.krnl32.shoppy.dto.checkout.CheckoutResponseDTO;
import com.krnl32.shoppy.dto.checkout.WebhookRequestDTO;

public interface CheckoutService {
	CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequest);
	void handleWebhookRequest(WebhookRequestDTO webhookRequest);
}
