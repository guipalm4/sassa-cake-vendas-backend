package com.sassacakes.sales.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.sale.model.ItemSale;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private BigDecimal cost;
    private BigDecimal price;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = " product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private List<Category> categories = Lists.newArrayList();

    @JsonIgnore
    @OneToMany(mappedBy="id.product")
    @Builder.Default
    private Set<ItemSale> itens = new HashSet<>();

}

