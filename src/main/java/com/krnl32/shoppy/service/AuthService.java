package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.other.JwtResponseDTO;
import com.krnl32.shoppy.dto.other.LoginResponseDTO;
import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserLoginRequestDTO;
import com.krnl32.shoppy.dto.user.UserRegisterRequestDTO;
import com.krnl32.shoppy.entity.User;

public interface AuthService {
    UserDTO registerUser(UserRegisterRequestDTO userRequest);
	LoginResponseDTO loginUser(UserLoginRequestDTO userRequest);
	JwtResponseDTO refreshAccessToken(String refreshToken);
	User getAuthenticatedUser();
}
