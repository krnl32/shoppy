package com.krnl32.shoppy.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
	private String accessToken;
	private String refreshToken;
}
