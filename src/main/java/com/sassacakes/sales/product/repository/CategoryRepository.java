package com.sassacakes.sales.product.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sassacakes.sales.product.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Override
    Optional<Category> findById(Integer id);

    Optional<Category> findByName(String name);
}
