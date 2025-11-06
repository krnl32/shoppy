package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserPatchRequestDTO;
import com.krnl32.shoppy.dto.user.UserUpdateRequestDTO;
import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.exception.ResourceAlreadyExistsException;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import com.krnl32.shoppy.mapper.UserMapper;
import com.krnl32.shoppy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
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
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("User ID Not Found: " + id);
		}

		return userMapper.toDTO(user.get());
	}

	@Override
	public UserDTO update(Long id, UserUpdateRequestDTO userRequest) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("User ID Not Found: " + id);
		}

		if (userRepository.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
			throw new ResourceAlreadyExistsException("Email Already Exists");
		}

		if (userRepository.existsByUsernameAndIdNot(userRequest.getUsername(), id)) {
			throw new ResourceAlreadyExistsException("Username Already Exists");
		}

		userMapper.update(userRequest, user.get());
		User updatedUser = userRepository.save(user.get());
		return userMapper.toDTO(updatedUser);
	}

	@Override
	public UserDTO patch(Long id, UserPatchRequestDTO userRequest) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("User ID Not Found: " + id);
		}

		if (userRepository.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
			throw new ResourceAlreadyExistsException("Email Already Exists");
		}

		if (userRepository.existsByUsernameAndIdNot(userRequest.getUsername(), id)) {
			throw new ResourceAlreadyExistsException("Username Already Exists");
		}

		userMapper.patch(userRequest, user.get());
		User patchedUser = userRepository.save(user.get());
		return userMapper.toDTO(patchedUser);
	}

	@Override
	public void delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("User ID Not Found: " + id);
		}

		userRepository.delete(user.get());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));

		return new org.springframework.security.core.userdetails.User(
			user.getUsername(),
			user.getPassword(),
			Collections.emptyList()
		);
	}
}
