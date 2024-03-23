package org.example.eco_v2.productSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco_v2.product.dto.ProductResponseDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetResponseDto {
    private UUID id;
    private int quantity;
    private ProductResponseDto product;
}
