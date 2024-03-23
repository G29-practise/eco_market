package org.example.eco_v2.cart;

import org.example.eco_v2.cart.entity.Cart;
import org.example.eco_v2.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends GenericRepository<Cart, UUID> {
}
