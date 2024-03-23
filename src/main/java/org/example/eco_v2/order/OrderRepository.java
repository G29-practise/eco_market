package org.example.eco_v2.order;

import org.example.eco_v2.common.repository.GenericRepository;
import org.example.eco_v2.order.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends GenericRepository<Order, UUID> {
}
