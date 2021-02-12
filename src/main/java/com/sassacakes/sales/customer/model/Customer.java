package com.sassacakes.sales.customer.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.sale.model.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
