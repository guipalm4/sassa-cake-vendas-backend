package com.sassacakes.sales.sale.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.customer.model.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sale implements AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private LocalDateTime instant;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="sale")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy="id.sale")
    private Set<ItemSale> itens = new HashSet<>();


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ItemSale> getItens() {
        return itens;
    }

    public void setItens(Set<ItemSale> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equal(getId(), sale.getId()) && Objects.equal(getInstant(), sale.getInstant()) && Objects.equal(getPayment(), sale.getPayment()) && Objects.equal(getCustomer(), sale.getCustomer()) && Objects.equal(getItens(), sale.getItens());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getInstant(), getPayment(), getCustomer(), getItens());
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", instant=" + instant +
                ", payment=" + payment +
                ", customer=" + customer +
                ", itens=" + itens +
                '}';
    }
}
