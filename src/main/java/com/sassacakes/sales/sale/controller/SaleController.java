package com.sassacakes.sales.sale.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sassacakes.sales.core.dto.BasicResponse;
import com.sassacakes.sales.core.dto.InfoMessage;
import com.sassacakes.sales.core.dto.MessageType;
import com.sassacakes.sales.sale.dto.MethodPayment;
import com.sassacakes.sales.sale.model.Sale;
import com.sassacakes.sales.sale.service.SaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sale", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @ApiOperation(
            value = "Realiza uma venda",
            response = Sale.class,
            responseContainer = "CompletableFuture",
            nickname = "sell")
    @PostMapping(value = "/")
    public ResponseEntity<BasicResponse> sell(@ApiParam(value = "Instância da venda", required = true)
                                              @RequestBody Sale saleRequest) {

        LOGGER.info("Realizando venda...");

        Sale response = saleService.sell(saleRequest);

        return ResponseEntity
                .ok(BasicResponse.Builder.aBasicResponse().data(response)
                        .message(InfoMessage.Builder.anInfoMessage()
                                .text("Venda efetivada com sucesso.")
                                .type(MessageType.SUCCESS).build())
                        .build());

    }

    @ApiOperation(
            value = "Lista metodos de pagamento",
            responseContainer = "CompletableFuture",
            nickname = "list-payment-methods")
    @GetMapping(value = "/payment/methods")
    public ResponseEntity<List<MethodPayment>> getMethodsPayment() {

        LOGGER.info("Listando meios de pagamentos...");

        List<MethodPayment> paymentMethods = new ArrayList<>();
        for (MethodPayment method : MethodPayment.values()) {
            MethodPayment methodPayment = MethodPayment.valueOf(method.name());
            paymentMethods.add(methodPayment);
        }
        return ResponseEntity.ok(paymentMethods);
    }
}
