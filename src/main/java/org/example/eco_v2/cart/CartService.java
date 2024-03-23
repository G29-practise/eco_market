package org.example.eco_v2.cart;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco_v2.cart.dto.CartCreateDto;
import org.example.eco_v2.cart.dto.CartResponseDto;
import org.example.eco_v2.cart.dto.CartUpdateDto;
import org.example.eco_v2.cart.entity.Cart;
import org.example.eco_v2.common.service.GenericService;
import org.example.eco_v2.productSet.ProductSetRepository;
import org.example.eco_v2.user.UserRepository;
import org.example.eco_v2.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class CartService extends GenericService<Cart, UUID, CartCreateDto, CartResponseDto, CartUpdateDto> {
    private final CartRepository repository;
    private final Class<Cart> entityClass= Cart.class;
    private final CartDtoMapper mapper;
    private final UserRepository userRepository;
    private final ProductSetRepository productSetRepository;

    @Override
    protected CartResponseDto internalCreate(CartCreateDto cartCreateDto) {
        Cart cart = mapper.toEntity(cartCreateDto);
        UUID userId = cartCreateDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        cart.setId(UUID.randomUUID());
        cart.setUser(user);

        userRepository.save(user);

        Cart saved = repository.saveAndFlush(cart);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected CartResponseDto internalUpdate(UUID uuid, CartUpdateDto cartUpdateDto) {
        Cart cart = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(cartUpdateDto,cart);
        repository.save(cart);

        return mapper.toResponseDto(cart);
    }
}
