package org.example.eco_v2.user.permission;

import org.example.eco_v2.common.repository.GenericRepository;
import org.example.eco_v2.user.permission.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends GenericRepository<Permission, String> {
}
