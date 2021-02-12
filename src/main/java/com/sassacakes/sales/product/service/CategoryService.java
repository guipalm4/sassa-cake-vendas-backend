package com.sassacakes.sales.product.service;

import static com.sassacakes.sales.core.error.SassaCakesError.CATEGORY_NOT_FOUND;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

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
        log.info("Salvando categoria. Categoria: [{}]", category.getName());
        return categoryRepository.save(category);
    }


}
