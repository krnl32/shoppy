package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.user.UserDTO;

import java.util.List;

public interface AdminService {
	List<UserDTO> findAll();
}
