package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.order.OrderDTO;
import com.krnl32.shoppy.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	@Mapping(source = "customer.username", target = "customer")
	OrderDTO toDTO(Order order);
}
