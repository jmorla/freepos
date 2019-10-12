package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Providers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvidersRepository extends JpaRepository<Providers, String> {

    @Query("select true from Providers p where p.email= :email")
    Optional<Providers> validateIfExistEmail(@Param("email") String email);

    @Query("select p from Providers p where p.providerId= :id")
    Providers buscarPorId(@Param("id") String id);
}
