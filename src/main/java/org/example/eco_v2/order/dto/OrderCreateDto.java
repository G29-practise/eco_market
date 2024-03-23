package org.example.eco_v2.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto{
    private UUID userId;
    private String city;
    private String country;
    private int postCode;
    private String region;
}
