package com.krnl32.shoppy.mapper;

import com.krnl32.shoppy.dto.cart.CartDTO;
import com.krnl32.shoppy.dto.cart.CartItemDTO;
import com.krnl32.shoppy.entity.Cart;
import com.krnl32.shoppy.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
	CartDTO toDTO(Cart cart);
	CartItemDTO toDTO(CartItem cartItem);
}
