package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserPatchRequestDTO;
import com.krnl32.shoppy.dto.user.UserUpdateRequestDTO;
import com.krnl32.shoppy.exception.UserAlreadyExistsException;
import com.krnl32.shoppy.exception.UserNotFoundException;
import com.krnl32.shoppy.mapper.UserMapper;
import com.krnl32.shoppy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO findById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found: " + id);
        }

        return userMapper.toDTO(user.get());
    }

    @Override
    public UserDTO update(Long id, UserUpdateRequestDTO userRequest) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found: " + id);
        }

        if (userRepository.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
            throw new UserAlreadyExistsException("Email Already Exists");
        }

        if (userRepository.existsByUsernameAndIdNot(userRequest.getUsername(), id)) {
            throw new UserAlreadyExistsException("Username Already Exists");
        }

        userMapper.update(userRequest, user.get());
        var updatedUser = userRepository.save(user.get());
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public UserDTO patch(Long id, UserPatchRequestDTO userRequest) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found: " + id);
        }

        if (userRepository.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
            throw new UserAlreadyExistsException("Email Already Exists");
        }

        if (userRepository.existsByUsernameAndIdNot(userRequest.getUsername(), id)) {
            throw new UserAlreadyExistsException("Username Already Exists");
        }

        userMapper.patch(userRequest, user.get());
        var patchedUser = userRepository.save(user.get());
        return userMapper.toDTO(patchedUser);
    }

    @Override
    public void delete(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found: " + id);
        }

        userRepository.delete(user.get());
    }
}
