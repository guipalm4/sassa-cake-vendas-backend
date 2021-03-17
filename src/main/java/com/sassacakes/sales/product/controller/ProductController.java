package com.sassacakes.sales.product.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sassacakes.sales.core.dto.BasicResponse;
import com.sassacakes.sales.core.dto.InfoMessage;
import com.sassacakes.sales.core.dto.MessageType;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.dto.ProductDto;
import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
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
                                              @RequestBody CreateProductRequest request) throws IOException {

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
            value = "Salva imagem do produto",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "salva-imagem-produto")
    @PostMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BasicResponse> imageUpload(@ApiParam(value = "Foto do produto", required = true)
                                                    @RequestPart(name = "file") MultipartFile file,
                                                    @ApiParam(value = "ID do produto", required = true)
                                                    @RequestParam("productId") Integer productId) throws IOException {

        LOGGER.info("Salvando foto do produto. ID: [{}]", productId);
        Product response = productService.uploadImage(productId, file);

        return ResponseEntity
                .ok(BasicResponse.Builder.aBasicResponse().data(response)
                        .message(InfoMessage.Builder.anInfoMessage()
                                .text("Imagem do produto cadastrada com sucesso.")
                                .type(MessageType.SUCCESS).build())
                        .build());
    }


    @ApiOperation(
            value = "Lista todos os Produtos",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "lista-todos-produtos")
    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> list() {

        LOGGER.info("Listando os produtos...");

        return ResponseEntity.ok(productService.getAll());

    }

    @ApiOperation(
            value = "Busca Produto por ID",
            response = Product.class,
            responseContainer = "CompletableFuture",
            nickname = "pesquisa-produto-por-id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@ApiParam(value = "Id do Produto", required = true)
                                            @PathVariable Integer id) {

        LOGGER.info("Pesquisando produto por id [{}]", id);
        return ResponseEntity.ok(productService.findById(id));
    }
}
