package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Permission;
import com.freelancer.billing.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{

    List<Permission> findByRoles(Role role);
}
