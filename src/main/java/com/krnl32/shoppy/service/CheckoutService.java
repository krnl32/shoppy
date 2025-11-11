package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.checkout.CheckoutRequestDTO;
import com.krnl32.shoppy.dto.checkout.CheckoutResponseDTO;

public interface CheckoutService {
	CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequest);
}
