package org.example.eco_v2.productSet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco_v2.cart.entity.Cart;
import org.example.eco_v2.order.entity.Order;
import org.example.eco_v2.product.entity.Product;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductSet {
    @Id
    private UUID id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToMany(mappedBy = "products")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders;
}
