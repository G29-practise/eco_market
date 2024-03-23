package org.example.eco_v2.address;

import org.example.eco_v2.address.entity.Address;
import org.example.eco_v2.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends GenericRepository<Address, UUID> {
}
