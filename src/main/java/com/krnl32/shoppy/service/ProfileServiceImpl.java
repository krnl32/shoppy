package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.profile.ProfileCreateRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileDTO;
import com.krnl32.shoppy.dto.profile.ProfilePatchRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileUpdateRequestDTO;
import com.krnl32.shoppy.entity.Profile;
import com.krnl32.shoppy.exception.ProfileNotFoundException;
import com.krnl32.shoppy.exception.UserNotFoundException;
import com.krnl32.shoppy.exception.ProfileAlreadyExistsException;
import com.krnl32.shoppy.mapper.ProfileMapper;
import com.krnl32.shoppy.repository.ProfileRepository;
import com.krnl32.shoppy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;
	private final ProfileMapper profileMapper;

	@Override
	public List<ProfileDTO> findAll() {
		return profileRepository.findAll()
			.stream().map(profileMapper::toDTO)
			.toList();
	}

	@Override
	public ProfileDTO findById(Long id) {
		var profile = profileRepository.findById(id);
		if (profile.isEmpty()) {
			throw new ProfileNotFoundException("Profile ID Not Found: " + id);
		}

		return profileMapper.toDTO(profile.get());
	}

	@Override
	public ProfileDTO create(ProfileCreateRequestDTO profileRequest) {
		var user = userRepository.findById(profileRequest.getUserId());
		if (user.isEmpty()) {
			throw new UserNotFoundException(("User ID Not Found: " + profileRequest.getUserId()));
		}

		if (user.get().getProfile() != null) {
			throw new ProfileAlreadyExistsException("User ID Already Has a Profile: " + profileRequest.getUserId());
		}

		Profile profile = profileMapper.toEntity(profileRequest);

		profile.setUser(user.get());
		user.get().setProfile(profile);

		var savedProfile = profileRepository.save(profile);
		return profileMapper.toDTO(savedProfile);
	}

	@Override
	public ProfileDTO update(Long id, ProfileUpdateRequestDTO profileRequest) {
		var profile = profileRepository.findById(id);
		if (profile.isEmpty()) {
			throw new ProfileNotFoundException("Profile ID Not Found: " + id);
		}

		profileMapper.update(profileRequest, profile.get());
		var updatedProfile = profileRepository.save(profile.get());
		return profileMapper.toDTO(updatedProfile);
	}

	@Override
	public ProfileDTO patch(Long id, ProfilePatchRequestDTO profileRequest) {
		var profile = profileRepository.findById(id);
		if (profile.isEmpty()) {
			throw new ProfileNotFoundException("Profile ID Not Found: " + id);
		}

		profileMapper.patch(profileRequest, profile.get());
		var patchedProfile = profileRepository.save(profile.get());
		return profileMapper.toDTO(patchedProfile);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		var profile = profileRepository.findById(id);
		if (profile.isEmpty()) {
			throw new ProfileNotFoundException("Profile ID Not Found: " + id);
		}

		profile.get().getUser().setProfile(null);
		profile.get().setUser(null);
		profileRepository.delete(profile.get());
	}
}
