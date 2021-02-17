package com.sassacakes.sales.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.sale.model.ItemSale;
import com.sassacakes.sales.sale.model.Sale;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity

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

    public Product() {
    }

    @JsonIgnore
    public List<Sale> getSales() {
        return itens.stream().map(ItemSale::getSale).collect(Collectors.toList());
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ItemSale> getItens() {
        return itens;
    }

    public void setItens(Set<ItemSale> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equal(getId(), product.getId()) && Objects.equal(getDescription(), product.getDescription()) && Objects.equal(getCost(), product.getCost()) && Objects.equal(getPrice(), product.getPrice()) && Objects.equal(getCategory(), product.getCategory()) && Objects.equal(getItens(), product.getItens());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getDescription(), getCost(), getPrice(), getCategory());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", category=" + category +
                ", itens=" + itens +
                '}';
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

