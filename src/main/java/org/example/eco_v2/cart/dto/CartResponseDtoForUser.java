package org.example.eco_v2.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco_v2.productSet.dto.ProductSetResponseDto;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDtoForUser {
    private UUID id;
    private Set<ProductSetResponseDto> products;
}
