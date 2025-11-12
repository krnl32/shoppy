package com.krnl32.shoppy.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class WebhookRequestDTO {
	private Map<String, String> headers;
	private String payload;
}
