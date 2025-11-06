package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.other.JwtResponseDTO;
import com.krnl32.shoppy.dto.other.LoginResponseDTO;
import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserLoginRequestDTO;
import com.krnl32.shoppy.dto.user.UserRegisterRequestDTO;
import com.krnl32.shoppy.entity.Role;
import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.exception.ResourceAlreadyExistsException;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import com.krnl32.shoppy.mapper.UserMapper;
import com.krnl32.shoppy.repository.UserRepository;
import com.krnl32.shoppy.type.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

    @Override
    public UserDTO registerUser(UserRegisterRequestDTO userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException("Email Already Exists");
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResourceAlreadyExistsException("Username Already Exists");
        }

        User user = userMapper.toEntity(userRequest);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

	@Override
	public LoginResponseDTO loginUser(UserLoginRequestDTO userRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

		User user = userRepository.findByUsername(userRequest.getUsername())
			.orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + userRequest.getUsername()));

		Jwt accessToken = jwtService.generateAccessToken(user);
		Jwt refreshToken = jwtService.generateRefreshToken(user);
		return new LoginResponseDTO(accessToken.toString(), refreshToken.toString());
	}

	@Override
	public JwtResponseDTO refreshAccessToken(String refreshToken) {
		Jwt jwt = jwtService.parseToken(refreshToken);
		if (jwt == null || jwt.isExpired()) {
			throw new BadCredentialsException("Bad Refresh Token: " + refreshToken);
		}

		User user = userRepository.findById(jwt.getUserId())
			.orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + jwt.getUserId()));

		Jwt accessToken = jwtService.generateAccessToken(user);
		return new JwtResponseDTO(accessToken.toString());
	}
}
