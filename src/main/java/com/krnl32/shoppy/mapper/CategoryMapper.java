package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.category.CategoryCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.category.CategoryDTO;
import com.krnl32.shoppy.dto.category.CategoryPatchRequestDTO;
import com.krnl32.shoppy.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryDTO toDTO(Category category);
	Category toEntity(CategoryCreateUpdateRequestDTO categoryRequest);

	@Mapping(target = "id", ignore = true)
	void update(CategoryCreateUpdateRequestDTO categoryRequest, @MappingTarget Category category);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(CategoryPatchRequestDTO categoryRequest, @MappingTarget Category category);
}
