package com.freelancer.billing.repository;

import com.freelancer.billing.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
