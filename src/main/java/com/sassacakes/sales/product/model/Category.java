package com.sassacakes.sales.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.sassacakes.sales.core.model.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy="category")
    @JsonIgnore
    private List<Product> products = Lists.newArrayList();

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equal(getId(), category.getId()) && Objects.equal(getName(), category.getName()) && Objects.equal(getProducts(), category.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getProducts());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
