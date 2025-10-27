package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.UserDTO;
import com.krnl32.shoppy.dto.UserRegisterRequestDTO;
import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.exception.UserAlreadyExistsException;
import com.krnl32.shoppy.mapper.UserMapper;
import com.krnl32.shoppy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO registerUser(UserRegisterRequestDTO userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email Already Exists");
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username Already Exists");
        }

        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
}
