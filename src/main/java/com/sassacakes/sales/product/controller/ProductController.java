package com.sassacakes.sales.product.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Product;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @ApiOperation(
            value = "Cria ou altera uma instancia de questionário",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "cria-ou-altera-questionario")
    @PutMapping(value = "/")
    public ResponseEntity<Product> save(CreateProductRequest request) {

        log.info("Salvando produto");

        return ResponseEntity.ok(Product.builder().build());

    }
}
