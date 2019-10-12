package com.freelancer.billing.repository;

import com.freelancer.billing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Jorge L. Morla
 * @version 1.0
 *
 * UserRepository address all queries related to USERS Table
 *
 * */
public interface UserRepository extends JpaRepository<User, String>{

    /**Finds a user by username*/
    Optional<User> findByUsername(String username);
}
