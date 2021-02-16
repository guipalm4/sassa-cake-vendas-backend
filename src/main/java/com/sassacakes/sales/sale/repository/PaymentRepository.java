package com.sassacakes.sales.sale.repository;

import com.sassacakes.sales.sale.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
