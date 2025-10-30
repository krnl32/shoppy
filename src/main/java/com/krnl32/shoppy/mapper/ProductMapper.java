package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.product.ProductCreateUpdateRequestDTO;
import com.krnl32.shoppy.dto.product.ProductDTO;
import com.krnl32.shoppy.dto.product.ProductPatchRequestDTO;
import com.krnl32.shoppy.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	Product toEntity(ProductDTO productDTO);
	Product toEntity(ProductCreateUpdateRequestDTO productDTO);

	@Mapping(source = "category.id", target = "categoryId")
	ProductDTO toDTO(Product product);

	@Mapping(target = "id", ignore = true)
	void update(ProductCreateUpdateRequestDTO productRequest, @MappingTarget Product product);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patch(ProductPatchRequestDTO productRequest, @MappingTarget Product product);
}
