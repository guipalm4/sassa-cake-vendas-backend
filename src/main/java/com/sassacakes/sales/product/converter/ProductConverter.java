package com.sassacakes.sales.product.converter;

import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product convert(CreateProductRequest request, Category category) {
        return Product.Builder.aProduct()
                .description(request.getDescription())
                .category(category)
                .price(request.getPrice())
                .cost(request.getCost()).build();
    }
}
