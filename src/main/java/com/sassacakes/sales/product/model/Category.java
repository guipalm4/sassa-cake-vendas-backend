package com.sassacakes.sales.product.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy="categories")
    private List<Product> products = new ArrayList<>();


    public Category(String name) {
        super();
        this.name = name;
    }
}
