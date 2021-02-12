package com.sassacakes.sales.product;

import org.springframework.stereotype.Component;

import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Product;

@Component
public class ProductConverter {

    public Product convert(CreateProductRequest request, Category category) {
        return Product.builder()
                .description(request.getDescription())
                .price(request.getPrice())
                .cost(request.getCost()).build();
    }
}
