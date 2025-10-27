package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.UserDTO;
import com.krnl32.shoppy.dto.UserPatchRequestDTO;
import com.krnl32.shoppy.dto.UserUpdateRequestDTO;
import com.krnl32.shoppy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDTO userRequest) {
        return ResponseEntity.ok(userService.update(userId, userRequest));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable Long userId, @Valid @RequestBody UserPatchRequestDTO userRequest) {
        return ResponseEntity.ok(userService.patch(userId, userRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
