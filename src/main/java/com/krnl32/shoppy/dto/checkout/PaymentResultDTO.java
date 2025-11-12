package com.krnl32.shoppy.dto.checkout;

import com.krnl32.shoppy.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResultDTO {
	private Long orderId;
	private PaymentStatus paymentStatus;
}
