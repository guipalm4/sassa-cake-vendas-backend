package com.sassacakes.sales.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.sale.model.Sale;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Customer implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String document;
    private String email;
    private BigDecimal credit;
    private boolean risk= Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy="customer")
    private List<Sale> sales = Lists.newArrayList();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public boolean isRisk() {
        return risk;
    }

    public void setRisk(boolean risk) {
        this.risk = risk;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return isRisk() == customer.isRisk() && Objects.equal(getId(), customer.getId()) && Objects.equal(getNome(), customer.getNome()) && Objects.equal(getDocument(), customer.getDocument()) && Objects.equal(getEmail(), customer.getEmail()) && Objects.equal(getCredit(), customer.getCredit());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getNome(), getDocument(), getEmail(), getCredit(), isRisk());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", credit=" + credit +
                ", risk=" + risk +
                ", sales=" + sales +
                '}';
    }
}
