package org.example.eco_v2.user.role;

import org.example.eco_v2.common.repository.GenericRepository;
import org.example.eco_v2.user.role.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends GenericRepository<Role,String> {
    Optional<Role> findByName(String name);
}
