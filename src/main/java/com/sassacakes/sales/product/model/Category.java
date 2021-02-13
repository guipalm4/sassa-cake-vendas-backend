package com.sassacakes.sales.product.model;

import java.util.List;

import javax.persistence.*;

import com.google.common.collect.Lists;
import com.sassacakes.sales.core.model.AbstractEntity;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Category implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy="category")
    private List<Product> products = Lists.newArrayList();

    public Category(String name) {
        this.name = name;
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
