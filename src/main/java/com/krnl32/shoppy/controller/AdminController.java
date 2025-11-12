package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	private final AdminService adminService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAdmins() {
		return ResponseEntity.ok(adminService.findAll());
	}
}
