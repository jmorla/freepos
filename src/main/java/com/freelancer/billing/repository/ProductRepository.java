package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, String>{

    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByProductId(@Param("id") String id);

    @Query("select p from Product p where p.barcode = :barcode")
    Optional<Product> findByBarcode(@Param("barcode") String barcode);

    @Query("select p from Product p where lower(p.name) like lower(concat(?1,'%')) ")
    List<Product> findByNameLike(@Param("name") String name);

    @Query("select p from Product p where p.category.id = :id")
    List<Product> findByCategoryId(@Param("id") String id);


}