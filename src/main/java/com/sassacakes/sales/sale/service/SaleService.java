package com.sassacakes.sales.sale.service;

import com.sassacakes.sales.customer.model.Customer;
import com.sassacakes.sales.customer.service.CustomerService;
import com.sassacakes.sales.product.service.ProductService;
import com.sassacakes.sales.sale.dto.MethodPayment;
import com.sassacakes.sales.sale.dto.StatePayment;
import com.sassacakes.sales.sale.model.ItemSale;
import com.sassacakes.sales.sale.model.Payment;
import com.sassacakes.sales.sale.model.Sale;
import com.sassacakes.sales.sale.repository.ItemSaleRepository;
import com.sassacakes.sales.sale.repository.PaymentRepository;
import com.sassacakes.sales.sale.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ItemSaleRepository itemSaleRepository;

    private Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Transactional
    public Sale sell(Sale saleRequest) {

        LOGGER.info("Iniciando processo de venda...");
        Sale sale = generateSale(saleRequest);
        Payment payment = sale.getPayment();

        if(payment.getMethod().equals(MethodPayment.ON_HAVING)) {
            insertOnHavingSale(sale);
        }
        return sale;
    }

    private void insertOnHavingSale(Sale sale) {
        LOGGER.info("Método 'Em haver' selecionado...");
        LOGGER.info("Localizando cliente pelo ID: [{}]", sale.getCustomer().getId());
        Customer customer = customerService.findById(sale.getCustomer().getId());
        BigDecimal total = calculateItemsSale(sale);

        LOGGER.info("Verificando limite disponivel. Cliente: [{}]", customer.getNome());
        customerService.verifyLimit(customer, total);
        LOGGER.info("Atualizando limite disponivel. Cliente: [{}]", customer.getNome());
        sale.getPayment().setEstate(StatePayment.PENDING);
        customerService.removeLimit(customer, total);
        customerService.save(customer);
    }

    private BigDecimal calculateItemsSale(Sale sale) {
        LOGGER.info("Calculando total da venda...");
        BigDecimal totalSale = BigDecimal.ZERO;
        for(ItemSale is: sale.getItens()) {
            totalSale = totalSale.add(is.getSubTotal());
        }
        LOGGER.info("Total da venda calculado. Total: [{}]", totalSale);
        return totalSale;
    }

    private Sale generateSale(Sale sale) {
        LOGGER.info("Gerando objeto de venda...");
        sale.setId(null);
        sale.setCustomer(customerService.find(sale.getCustomer().getId()).orElse(null));
        sale.setInstant(LocalDateTime.now());
        sale.getPayment().setEstate(StatePayment.PAID);
        sale.getPayment().setSale(sale);
        Sale saved = this.save(sale);
        paymentRepository.save(sale.getPayment());
        LOGGER.info("Objeto criado. Venda: [{}]", saved);
        return generateItemsSale(saved);
    }

    private Sale generateItemsSale(Sale sale) {
        LOGGER.info("Atualizando itens da venda...");
        for (ItemSale is : sale.getItens()) {
            is.setProduct(productService.findById(is.getProduct().getId()));
            is.setPrice(is.getProduct().getPrice());
            is.setSale(sale);
        }
        itemSaleRepository.saveAll(sale.getItens());
        LOGGER.info("Venda atualizada. Itens: [{}]" , sale.getItens());
        return sale;
    }

}
