package org.example.eco_v2.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco_v2.product.dto.ProductResponseDto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistUpdateDto {
    private Set<ProductResponseDto> products;
}
