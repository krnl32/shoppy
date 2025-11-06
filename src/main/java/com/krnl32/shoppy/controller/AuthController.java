package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.config.JwtConfig;
import com.krnl32.shoppy.dto.other.JwtResponseDTO;
import com.krnl32.shoppy.dto.other.LoginResponseDTO;
import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserLoginRequestDTO;
import com.krnl32.shoppy.dto.user.UserRegisterRequestDTO;
import com.krnl32.shoppy.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
	private final JwtConfig jwtConfig;

	@PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRequest, UriComponentsBuilder uriBuilder) {
        UserDTO userDTO = authService.registerUser(userRequest);
		URI uri = uriBuilder.path("/api/users/{userId}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> loginUser(@Valid @RequestBody UserLoginRequestDTO userRequest, HttpServletResponse httpServletResponse) {
		LoginResponseDTO loginResponseDTO = authService.loginUser(userRequest);

		Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
		cookie.setPath("/api/auth/refresh");
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge((int) jwtConfig.getRefreshTokenExpiration());
		httpServletResponse.addCookie(cookie);

		return ResponseEntity.ok(new JwtResponseDTO(loginResponseDTO.getAccessToken()));
	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtResponseDTO> refreshAccessToken(@CookieValue(value = "refreshToken") String refreshToken) {
		return ResponseEntity.ok(authService.refreshAccessToken(refreshToken));
	}
}
