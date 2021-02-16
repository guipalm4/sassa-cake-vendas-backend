package com.sassacakes.sales.sale.repository;

import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.sale.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Integer> {

    @Override
    Optional<Sale> findById(Integer integer);

}
