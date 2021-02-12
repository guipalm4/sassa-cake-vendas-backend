package com.sassacakes.sales.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sassacakes.sales.customer.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Override
    Optional<Customer> findById(Integer integer);
}
