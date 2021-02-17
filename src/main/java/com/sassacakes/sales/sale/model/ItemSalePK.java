package com.sassacakes.sales.sale.model;

import com.google.common.base.Objects;
import com.sassacakes.sales.product.model.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ItemSalePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemSalePK)) return false;
        ItemSalePK that = (ItemSalePK) o;
        return Objects.equal(getSale(), that.getSale()) && Objects.equal(getProduct(), that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSale(), getProduct());
    }
}
