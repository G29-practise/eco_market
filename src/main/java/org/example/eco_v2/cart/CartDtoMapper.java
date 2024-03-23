package org.example.eco_v2.cart;

import lombok.RequiredArgsConstructor;
import org.example.eco_v2.cart.dto.CartCreateDto;
import org.example.eco_v2.cart.dto.CartResponseDto;
import org.example.eco_v2.cart.dto.CartUpdateDto;
import org.example.eco_v2.cart.entity.Cart;
import org.example.eco_v2.common.mapper.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartDtoMapper extends GenericMapper<Cart, CartCreateDto, CartResponseDto, CartUpdateDto> {
    private final ModelMapper mapper;
    @Override
    public Cart toEntity(CartCreateDto cartCreateDto) {
        return mapper.map(cartCreateDto,Cart.class);
    }

    @Override
    public CartResponseDto toResponseDto(Cart cart) {
        return mapper.map(cart, CartResponseDto.class);
    }

    @Override
    public void toEntity(CartUpdateDto cartUpdateDto, Cart cart) {
       mapper.map(cartUpdateDto,cart);
    }
}
