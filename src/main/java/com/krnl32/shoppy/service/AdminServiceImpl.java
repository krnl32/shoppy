package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.entity.Role;
import com.krnl32.shoppy.mapper.UserMapper;
import com.krnl32.shoppy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream()
			.filter(user -> user.getRole() == Role.ADMIN)
			.map(userMapper::toDTO)
			.toList();
	}
}
