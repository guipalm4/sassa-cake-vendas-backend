package com.sassacakes.sales.product.controller;

import com.sassacakes.sales.core.dto.BasicResponse;
import com.sassacakes.sales.core.dto.InfoMessage;
import com.sassacakes.sales.core.dto.MessageType;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(
            value = "Cria ou altera uma instancia de produto",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "cria-ou-altera-produto")
    @PutMapping(value = "/")
    public ResponseEntity<BasicResponse> save(@ApiParam(value = "Instância do Produto", required = true)
                                        @RequestBody CreateProductRequest request) {

        LOGGER.info("Salvando produto. Requisicão : [{}]", request);

        Product response = productService.createProduct(request);

        return ResponseEntity
                .ok(BasicResponse.Builder.aBasicResponse().data(response)
                        .message(InfoMessage.Builder.anInfoMessage()
                                .text("Produto cadastrado com sucesso.")
                                .type(MessageType.SUCCESS).build())
                        .build());

    }

    @ApiOperation(
            value = "Lista todos os Produtos",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "cria-ou-altera-produto")
    @GetMapping(value = "/")
    public ResponseEntity<Iterable<Product>> list() {

        LOGGER.info("Listando os produtos...");

        return ResponseEntity.ok(productService.getAll());

    }

    @ApiOperation(
            value = "Busca Produto por ID",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "cria-ou-altera-produto")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@ApiParam(value = "Id do Produto", required = true)
                                             @PathVariable Integer id) {

        LOGGER.info("Pesquisando produto por id [{}]", id);
        return ResponseEntity.ok(productService.findById(id));
    }
}
