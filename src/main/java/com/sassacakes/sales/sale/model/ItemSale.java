package com.sassacakes.sales.sale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.sassacakes.sales.product.model.Product;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class ItemSale implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemSalePK id = new ItemSalePK();

    private BigDecimal discount;
    private Integer quantity;
    private BigDecimal price;

    public ItemSale(Sale sale, Product product, BigDecimal discount, Integer quantity, BigDecimal price) {
        id.setSale(sale);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public ItemSale() {
    }

    @JsonIgnore
    public Sale getSale() {
        return id.getSale();
    }
    public void setSale(Sale sale) {
        id.setSale(sale);
    }

    public BigDecimal getSubTotal() {
        return (price.subtract(discount).multiply(BigDecimal.valueOf(quantity)));
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public ItemSalePK getId() {
        return id;
    }

    public void setId(ItemSalePK id) {
        this.id = id;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemSale)) return false;
        ItemSale itemSale = (ItemSale) o;
        return Objects.equal(getId(), itemSale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getProduct().getDescription());
        builder.append(", Qte: ");
        builder.append(getQuantity());
        builder.append(", Preço unitário: ");
        builder.append(getPrice());
        builder.append(", Subtotal: ");
        builder.append(getSubTotal());
        builder.append("\n");
        return builder.toString();
    }
}
