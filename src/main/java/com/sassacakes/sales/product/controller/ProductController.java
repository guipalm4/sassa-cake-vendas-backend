package com.sassacakes.sales.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(
            value = "Cria ou altera uma instancia de produto",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "cria-ou-altera-produto")
    @PutMapping(value = "/")
    public ResponseEntity<Product> save(@ApiParam(value = "Instância do Produto", required = true)
                                        @RequestBody CreateProductRequest request) {

        log.info("Salvando produto. Requisicão : [{}]", request);

        return ResponseEntity.ok(productService.createProduct(request));

    }
}
