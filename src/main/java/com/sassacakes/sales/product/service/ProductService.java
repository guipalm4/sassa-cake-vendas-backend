package com.sassacakes.sales.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.sassacakes.sales.product.converter.ProductConverter;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.product.repository.ProductRepository;
import com.sassacakes.sales.core.exception.SassaCakesError;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private MessageSourceAccessor message;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() ->
                SassaCakesError.PRODUCT_NOT_FOUND.asNotFoundException(message,id));
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product createProduct(CreateProductRequest request) {
        log.info("Verificando categoria. Categoria: [{}]", request.getDescription());
        Category category = categoryService.findByName(request.getDescription())
                .orElse(categoryService.save(new Category(request.getDescription())));

        Product product = productConverter.convert(request, category);
        return this.save(product);
    }


}
