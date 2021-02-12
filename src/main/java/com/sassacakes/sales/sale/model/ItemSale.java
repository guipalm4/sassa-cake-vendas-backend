package com.sassacakes.sales.sale.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sassacakes.sales.core.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSale implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemSalePK id = new ItemSalePK();

    private BigDecimal discount;
    private Integer quantity;
    private BigDecimal price;

    @JsonIgnore
    public Sale getSale() {
        return id.getSale();
    }

    public BigDecimal getSubTotal() {
        return (price.subtract(discount).multiply(BigDecimal.valueOf(quantity)));
    }

}
