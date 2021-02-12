package com.sassacakes.sales.product.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sassacakes.sales.product.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer integer);
}
