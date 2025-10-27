package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.UserDTO;
import com.krnl32.shoppy.dto.UserRegisterRequestDTO;
import com.krnl32.shoppy.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRequest, UriComponentsBuilder uriBuilder) {
        UserDTO userDTO = authService.registerUser(userRequest);
        var uri = uriBuilder.path("/users/{userId}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }
}
