package com.sassacakes.sales.product.model;

import java.util.List;

import javax.persistence.*;

import com.google.common.collect.Lists;
import com.sassacakes.sales.core.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products = Lists.newArrayList();

    public Category(String name) {
        this.name = name;
    }
}
