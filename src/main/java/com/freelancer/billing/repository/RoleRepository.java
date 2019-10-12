package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByUsersId(String uuid);
}
