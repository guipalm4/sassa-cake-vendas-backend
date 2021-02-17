package com.sassacakes.sales.sale.dto;

public enum MethodPayment {

    MONEY("Dinheiro"),
    CREDIT_CARD("Cartão"),
    ON_HAVING("A haver");

    private String description;

    MethodPayment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
