package com.sassacakes.sales.product.dto;

import com.google.common.base.Objects;

import java.math.BigDecimal;

public class CreateProductRequest {

    private String name;
    private String description;
    private BigDecimal cost;
    private BigDecimal price;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductRequest)) return false;
        CreateProductRequest that = (CreateProductRequest) o;
        return Objects.equal(getDescription(), that.getDescription()) && Objects.equal(getCost(), that.getCost()) && Objects.equal(getPrice(), that.getPrice()) && Objects.equal(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDescription(), getCost(), getPrice(), getCategory());
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "description='" + description + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
