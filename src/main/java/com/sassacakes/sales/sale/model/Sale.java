package com.sassacakes.sales.sale.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.customer.model.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Sale implements AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime instant;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="sale")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy="id.sale")
    private Set<ItemSale> itens = new HashSet<>();

    public Sale(Integer id, LocalDateTime instant, Customer customer) {
        super();
        this.id = id;
        this.instant = instant;
        this.customer = customer;
    }

    public Sale() {}

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

    public BigDecimal getTotalValue() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemSale is : itens) {
            total = total.add(is.getSubTotal());
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equal(getId(), sale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(getInstant());
        builder.append(", Cliente: ");
        builder.append(getCustomer().getNome());
        builder.append(", Situação do pagamento: ");
        builder.append(getPayment().getEstate().getDescription());
        builder.append("\nDetalhes:\n");
        for (ItemSale ip : getItens()) {
            builder.append(ip.toString());
        }
        builder.append("Valor total: ");
        builder.append(getTotalValue());
        return builder.toString();
    }
}
