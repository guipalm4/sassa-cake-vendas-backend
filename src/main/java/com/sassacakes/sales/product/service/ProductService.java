package com.sassacakes.sales.product.service;

import com.sassacakes.sales.core.error.SassaCakesError;
import com.sassacakes.sales.product.converter.ProductConverter;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

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

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product createProduct(CreateProductRequest request) {
        LOGGER.info("Verificando categoria. Categoria: [{}]", request.getDescription());


        Category category = categoryService.findByName(request.getCategory())
                .orElse(new Category(request.getCategory()));

        Product product = productConverter.convert(request, category);
        category.getProducts().add(product);

        categoryService.save(category);
        return this.save(product);
    }

}
