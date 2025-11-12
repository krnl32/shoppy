package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.checkout.PaymentResultDTO;
import com.krnl32.shoppy.dto.checkout.WebhookRequestDTO;
import com.krnl32.shoppy.entity.Order;
import com.krnl32.shoppy.type.CheckoutSession;

import java.util.Optional;

public interface PaymentService {
	CheckoutSession createCheckoutSession(Order order);
	Optional<PaymentResultDTO> handleWebhookRequest(WebhookRequestDTO webhookRequest);
}
