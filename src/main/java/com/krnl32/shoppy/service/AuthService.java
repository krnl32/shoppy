package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserRegisterRequestDTO;

public interface AuthService {
    UserDTO registerUser(UserRegisterRequestDTO userRequest);
}
