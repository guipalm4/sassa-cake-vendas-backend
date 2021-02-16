package com.sassacakes.sales.customer.service;

import com.sassacakes.sales.core.error.SassaCakesError;
import com.sassacakes.sales.customer.model.Customer;
import com.sassacakes.sales.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.sassacakes.sales.core.error.SassaCakesError.CUSTOMER_LIMIT_EXCEEDED;

@Service
public class CustomerService {

    @Autowired
    private MessageSourceAccessor message;

    @Autowired
    private CustomerRepository customerRepository;

    private final BigDecimal limit;

    public CustomerService(@Value("${customer.limit.value}") BigDecimal limit) {
        this.limit = limit;
    }

    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() ->
                SassaCakesError.CUSTOMER_NOT_FOUND.asNotFoundException(message,id));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer register(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setNome(customer.getNome());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setCredit(this.limit);
        newCustomer.setDocument(customer.getDocument());
        return this.save(newCustomer);
    }


    public void verifyLimit(Customer customer, BigDecimal orderValue) {
        if(limitExceeded(customer.getCredit(), orderValue)) {
            throw CUSTOMER_LIMIT_EXCEEDED.asNotFoundException(message, customer.getCredit());
        }

    }
    private boolean limitExceeded (BigDecimal limitValue, BigDecimal orderValue) {
        return limitValue.subtract(orderValue).compareTo(BigDecimal.ZERO) < 0;
    }

    public void removeLimit(Customer customer, BigDecimal orderValue) {
        BigDecimal actual = customer.getCredit();
        BigDecimal update = actual.subtract(orderValue);
        customer.setCredit(update);
        this.save(customer);
    }

    public void addCredit(Customer customer, BigDecimal value) {
        BigDecimal actual = customer.getCredit();
        BigDecimal update = actual.add(value);
        customer.setCredit(update);
        this.save(customer);
    }

    public void setRisk(Customer customer) {
        customer.setRisk(Boolean.TRUE);
        this.save(customer);
    }

}
