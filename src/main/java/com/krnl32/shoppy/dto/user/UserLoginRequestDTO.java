package com.krnl32.shoppy.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDTO {
	@NotBlank(message = "Username is Required")
	private String username;

	@NotBlank(message = "Password is Required")
	private String password;
}
