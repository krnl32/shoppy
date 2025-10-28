package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.profile.ProfileCreateRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileDTO;
import com.krnl32.shoppy.dto.profile.ProfilePatchRequestDTO;
import com.krnl32.shoppy.dto.profile.ProfileUpdateRequestDTO;
import com.krnl32.shoppy.entity.Profile;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
	Profile toEntity(ProfileDTO profileDTO);
	Profile toEntity(ProfileCreateRequestDTO profileRequest);

	@Mapping(source = "user.id", target = "userId")
	ProfileDTO toDTO(Profile profile);

	@Mapping(target = "id", ignore = true)
	void update(ProfileUpdateRequestDTO profileRequest, @MappingTarget Profile profile);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(ProfilePatchRequestDTO profileRequest, @MappingTarget Profile profile);
}
