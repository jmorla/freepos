package com.freelancer.billing.service.impl;

import com.freelancer.billing.domain.Category;
import com.freelancer.billing.repository.CategoryRepository;
import com.freelancer.billing.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        LOGGER.info("looking for categories");

        return categoryRepository.findAll();
    }

    @Override
    public Category guardar(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category actualizar(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category eliminar(Category category) {
        categoryRepository.delete(category);
        return category;
    }
}
