package org.example.eco_v2.address.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.eco_v2.order.entity.Order;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    private UUID id;
    private String city;
    private String country;
    private int postCode;
    private String region;
    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;
}
