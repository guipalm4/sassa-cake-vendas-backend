package com.sassacakes.sales.customer.repository;

import com.sassacakes.sales.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Override
    Optional<Customer> findById(Integer integer);
}
