package org.example.eco_v2.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco_v2.product.dto.ProductResponseDto;
import org.example.eco_v2.user.dto.UserResponseDtoForCartAndOrder;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponseDto {
    private UUID id;
    private UserResponseDtoForCartAndOrder user;
    private Set<ProductResponseDto> products;
}
