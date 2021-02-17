package com.sassacakes.sales.sale.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.sale.dto.MethodPayment;
import com.sassacakes.sales.sale.dto.StatePayment;

import javax.persistence.*;


@Entity
public class Payment implements AbstractEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="sale_id")
    @MapsId
    private Sale sale;

    private StatePayment estate;

    private MethodPayment method;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public StatePayment getEstate() {
        return estate;
    }

    public void setEstate(StatePayment estate) {
        this.estate = estate;
    }

    public MethodPayment getMethod() {
        return method;
    }

    public void setMethod(MethodPayment method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equal(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", estate=" + estate +
                ", method=" + method +
                '}';
    }
}
