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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private BigDecimal cost;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy="id.product")
    private Set<ItemSale> itens = new HashSet<>();

    public Product(Integer id, String description, BigDecimal cost, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }


    public static final class Builder {
        private Integer id;
        private String description;
        private BigDecimal cost;
        private BigDecimal price;
        private Category category;

        private Builder() {
        }

        public static Builder aProduct() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.setDescription(description);
            product.setCost(cost);
            product.setPrice(price);
            product.setCategory(category);
            return product;
        }
    }
}

