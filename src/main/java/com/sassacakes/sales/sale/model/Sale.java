package com.sassacakes.sales.sale.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.customer.model.Customer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
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

}
