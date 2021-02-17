package com.sassacakes.sales.product.repository;

import com.sassacakes.sales.product.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Override
    Optional<Category> findById(Integer id);

    Optional<Category> findByName(String name);
}
