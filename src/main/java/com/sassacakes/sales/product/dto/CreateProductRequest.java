package com.sassacakes.sales.product.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreateProductRequest {

    private String description;
    private BigDecimal cost;
    private BigDecimal price;
    private String category;
}
