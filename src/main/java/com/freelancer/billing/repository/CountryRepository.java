package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer>{ }
