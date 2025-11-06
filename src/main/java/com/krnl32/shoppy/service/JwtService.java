package com.krnl32.shoppy.service;

import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.type.Jwt;

public interface JwtService {
	Jwt generateAccessToken(User user);
	Jwt generateRefreshToken(User user);
	Jwt parseToken(String token);
}
