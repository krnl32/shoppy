package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.profile.ProfileCreateRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileDTO;
import com.krnl32.shoppy.dto.profile.ProfilePatchRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileUpdateRequestDTO;

import java.util.List;

public interface ProfileService {
	List<ProfileDTO> findAll();
	ProfileDTO findById(Long id);
	ProfileDTO create(ProfileCreateRequestDTO profileRequest);
	ProfileDTO update(Long id, ProfileUpdateRequestDTO profileRequest);
	ProfileDTO patch(Long id, ProfilePatchRequestDTO profileRequest);
	void delete(Long id);
}
