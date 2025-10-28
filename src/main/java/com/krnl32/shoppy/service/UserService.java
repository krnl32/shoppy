package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserPatchRequestDTO;
import com.krnl32.shoppy.dto.user.UserUpdateRequestDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO update(Long id, UserUpdateRequestDTO userRequest);
    UserDTO patch(Long id, UserPatchRequestDTO userRequest);
    void delete(Long id);
}
