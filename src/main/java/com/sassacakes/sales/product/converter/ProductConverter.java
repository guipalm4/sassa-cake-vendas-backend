package com.sassacakes.sales.product.converter;

import com.sassacakes.sales.core.util.Currencies;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.dto.ProductDto;
import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product convert(CreateProductRequest request, Category category) {
        return Product.Builder.aProduct()
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .price(request.getPrice())
                .cost(request.getCost()).build();
    }

    public ProductDto convert(Product product) {
        return ProductDto.Builder.aProductDto()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(Currencies.format(product.getPrice()))
                .cost(product.getCost())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();

    }
}
