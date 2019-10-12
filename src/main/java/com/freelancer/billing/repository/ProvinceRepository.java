package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer>{

    @Query("select p from Province p join p.country c where c.id = :id")
    List<Province> findProvinceByCountryId(@Param("id") Integer name);

    @Query("select p from Province p join p.country c where p.id = :id and c.id = :country")
    Optional<Province> findByNameAndCountry(@Param("id") Integer name, @Param("country") Integer country);
}
