package com.sassacakes.sales.sale.controller;

import com.sassacakes.sales.sale.dto.MethodPayment;
import com.sassacakes.sales.sale.model.Sale;
import com.sassacakes.sales.sale.service.SaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "sale", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {

    @Autowired
    private SaleService saleService;

    @ApiOperation(
            value = "Realiza uma venda",
            response = Sale.class,
            responseContainer = "CompletableFuture",
            nickname = "sell")
    @PostMapping(value = "/")
    public ResponseEntity<Sale> sell(@ApiParam(value = "Instância da venda", required = true)
                                        @RequestBody Sale saleRequest) {

        log.info("Realizando venda. Requisicão : [{}]", saleRequest);

        return ResponseEntity.ok(saleService.sell(saleRequest));

    }

    @ApiOperation(
            value = "Lista metodos de pagamento",
            responseContainer = "CompletableFuture",
            nickname = "list-payment-methods")
    @GetMapping(value = "/payment/methods")
    public ResponseEntity<MethodPayment[]> getMethodsPayment() {
        return ResponseEntity.ok(MethodPayment.values());        
    }
}
