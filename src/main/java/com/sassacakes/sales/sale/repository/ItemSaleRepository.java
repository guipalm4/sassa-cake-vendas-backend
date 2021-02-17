package com.sassacakes.sales.sale.repository;

import com.sassacakes.sales.sale.model.ItemSale;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemSaleRepository extends CrudRepository<ItemSale, Integer> {

    @Override
    Optional<ItemSale> findById(Integer integer);

}