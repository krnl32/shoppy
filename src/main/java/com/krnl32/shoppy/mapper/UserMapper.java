package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.UserDTO;
import com.krnl32.shoppy.dto.UserPatchRequestDTO;
import com.krnl32.shoppy.dto.UserRegisterRequestDTO;
import com.krnl32.shoppy.dto.UserUpdateRequestDTO;
import com.krnl32.shoppy.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserRegisterRequestDTO userRequest);
    void update(UserUpdateRequestDTO userRequest, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(UserPatchRequestDTO userRequest, @MappingTarget User user);
}
