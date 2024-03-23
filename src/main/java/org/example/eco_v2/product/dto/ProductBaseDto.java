package org.example.eco_v2.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBaseDto {
    private String title;
    private String description;
    private String brand;
    private int quantity;
    private int discount;
    private double weight;
    private double price;
}
