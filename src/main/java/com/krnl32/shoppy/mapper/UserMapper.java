package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.user.UserDTO;
import com.krnl32.shoppy.dto.user.UserPatchRequestDTO;
import com.krnl32.shoppy.dto.user.UserRegisterRequestDTO;
import com.krnl32.shoppy.dto.user.UserUpdateRequestDTO;
import com.krnl32.shoppy.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserRegisterRequestDTO userRequest);

	@Mapping(target = "id", ignore = true)
    void update(UserUpdateRequestDTO userRequest, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(UserPatchRequestDTO userRequest, @MappingTarget User user);
}
