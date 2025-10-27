package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.UserDTO;
import com.krnl32.shoppy.dto.UserRegisterRequestDTO;

public interface AuthService {
    UserDTO registerUser(UserRegisterRequestDTO userRequest);
}
