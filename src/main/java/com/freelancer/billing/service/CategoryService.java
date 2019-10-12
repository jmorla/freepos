package com.freelancer.billing.service;

import com.freelancer.billing.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category guardar(Category category);

    Category actualizar(Category category);

    Category eliminar(Category category);
}
