package com.sassacakes.sales.product.service;

import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sassacakes.sales.core.error.SassaCakesError.CATEGORY_NOT_FOUND;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private MessageSourceAccessor message;

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() ->CATEGORY_NOT_FOUND.asNotFoundException(message,id));
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category save(Category category) {
        LOGGER.info("Salvando categoria. Categoria: [{}]", category.getName());
        return categoryRepository.save(category);
    }


}
