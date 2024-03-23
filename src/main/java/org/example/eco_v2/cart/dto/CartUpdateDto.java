package org.example.eco_v2.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco_v2.productSet.dto.ProductSetResponseDto;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateDto{
    private Set<ProductSetResponseDto> products;
}
