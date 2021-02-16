package com.sassacakes.sales.customer.controller;

import com.sassacakes.sales.customer.model.Customer;
import com.sassacakes.sales.customer.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @ApiOperation(
            value = "Cria ou altera uma instancia de cliente",
            response = Customer.class,
            responseContainer = "CompletableFuture",
            nickname = "cria-ou-altera-produto")
    @PostMapping(value = "/")
    public ResponseEntity<Customer> save(@ApiParam(value = "Instância do Cliente", required = true)
                                        @RequestBody Customer request) {

        log.info("Salvando cliente. Requisicão : [{}]", request);

        return ResponseEntity.ok(customerService.save(request));

    }
}
