package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String>{

    @Query("select c from Customer c where lower(c.fullName) like lower(concat('%',?1, '%')) or c.socialId like concat('%',?1, '%')")
    List<Customer> findByFullNameOrSocialIdLike(String nameOrSocialId);

    @Query("select true from Customer c where c.email = :email")
    Optional<Boolean> existByEmail(@Param("email") String email);

    @Query("select true from Customer c where c.socialId = :socialId")
    Optional<Boolean> existBySocialId(@Param("socialId") String socialId);

    @Query("select true from Customer c where c.phoneNumber = :phone")
    Optional<Boolean> existByPhoneNumber(@Param("phone") String phone);

    List<Customer> findAllByOrderByCreateAtAsc();
}
