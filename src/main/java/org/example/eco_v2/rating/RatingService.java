package org.example.eco_v2.rating;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco_v2.common.service.GenericService;
import org.example.eco_v2.product.ProductRepository;
import org.example.eco_v2.product.entity.Product;
import org.example.eco_v2.rating.dto.RatingCreateDto;
import org.example.eco_v2.rating.dto.RatingResponseDto;
import org.example.eco_v2.rating.dto.RatingUpdateDto;
import org.example.eco_v2.rating.entity.Rating;
import org.example.eco_v2.user.UserRepository;
import org.example.eco_v2.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class RatingService extends GenericService<Rating, UUID, RatingCreateDto, RatingResponseDto, RatingUpdateDto> {
    private final RatingRepository repository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Class<Rating> entityClass = Rating.class;
    private final RatingDtoMapper mapper;

    @Override
    protected RatingResponseDto internalCreate(RatingCreateDto ratingCreateDto) {
        Rating rating = mapper.toEntity(ratingCreateDto);

        UUID productId = ratingCreateDto.getProductId();
        UUID userId = ratingCreateDto.getUserId();

        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        rating.setUser(user);
        rating.setProduct(product);
        rating.setId(UUID.randomUUID());
        product.getRatings().add(rating);

        productRepository.save(product);
        userRepository.save(user);
        Rating saved = repository.save(rating);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected RatingResponseDto internalUpdate(UUID uuid, RatingUpdateDto ratingUpdateDto) {
        return null;
    }
}
