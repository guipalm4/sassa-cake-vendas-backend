package com.sassacakes.sales.product.repository;

import com.sassacakes.sales.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer integer);
}
