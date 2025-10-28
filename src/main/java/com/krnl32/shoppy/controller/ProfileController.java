package com.krnl32.shoppy.controller;

import com.krnl32.shoppy.dto.profile.ProfileCreateRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileDTO;
import com.krnl32.shoppy.dto.profile.ProfilePatchRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileUpdateRequestDTO;
import com.krnl32.shoppy.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
	private final ProfileService profileService;

	@GetMapping
	public ResponseEntity<List<ProfileDTO>> getProfiles() {
		return ResponseEntity.ok(profileService.findAll());
	}

	@GetMapping("/{profileId}")
	public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long profileId) {
		return ResponseEntity.ok(profileService.findById(profileId));
	}

	@PostMapping
	public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileCreateRequestDTO profileRequest, UriComponentsBuilder uriBuilder) {
		ProfileDTO profileDTO = profileService.create(profileRequest);
		var uri = uriBuilder.path("/profiles/{profileId}").buildAndExpand(profileDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(profileDTO);
	}

	@PutMapping("/{profileId}")
	public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long profileId, @Valid @RequestBody ProfileUpdateRequestDTO profileRequest) {
		return ResponseEntity.ok(profileService.update(profileId, profileRequest));
	}

	@PatchMapping("/{profileId}")
	public ResponseEntity<ProfileDTO> patchProfile(@PathVariable Long profileId, @Valid @RequestBody ProfilePatchRequestDTO profileRequest) {
		return ResponseEntity.ok(profileService.patch(profileId, profileRequest));
	}

	@DeleteMapping("/{profileId}")
	public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
		profileService.delete(profileId);
		return ResponseEntity.noContent().build();
	}
}
