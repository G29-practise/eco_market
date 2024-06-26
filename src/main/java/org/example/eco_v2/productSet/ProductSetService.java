package org.example.eco_v2.productSet;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco_v2.cart.CartRepository;
import org.example.eco_v2.cart.entity.Cart;
import org.example.eco_v2.common.service.GenericService;
import org.example.eco_v2.product.ProductRepository;
import org.example.eco_v2.product.entity.Product;
import org.example.eco_v2.productSet.dto.ProductSetCreateDto;
import org.example.eco_v2.productSet.dto.ProductSetResponseDto;
import org.example.eco_v2.productSet.dto.ProductSetUpdateDto;
import org.example.eco_v2.productSet.entity.ProductSet;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class ProductSetService extends GenericService<ProductSet, UUID, ProductSetCreateDto, ProductSetResponseDto, ProductSetUpdateDto> {
    private final ProductSetRepository repository;
    private final Class<ProductSet> entityClass = ProductSet.class;
    private final ProductSetDtoMapper mapper;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Override
    protected ProductSetResponseDto internalCreate(ProductSetCreateDto createDto) {
        ProductSet productSet = mapper.toEntity(createDto);
        productSet.setId(UUID.randomUUID());

        UUID productId = createDto.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productSet.setProduct(product);

        UUID cartId = createDto.getCartId();
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        productSet.setCart(cart);
        cart.getProducts().add(productSet);
        cartRepository.save(cart);

        productRepository.save(product);
        ProductSet saved = repository.save(productSet);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected ProductSetResponseDto internalUpdate(UUID uuid, ProductSetUpdateDto updateDto) {
        ProductSet productSet = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("ProductSet with id: %s not fount".formatted(uuid)));
        mapper.toEntity(updateDto, productSet);
        ProductSet saved = repository.save(productSet);

        return mapper.toResponseDto(saved);
    }

}
