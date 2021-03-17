package com.sassacakes.sales.sale.dto;

public enum MethodPayment {

    MONEY(1,"Dinheiro"),
    CREDIT_CARD(2,"Cartão"),
    ON_HAVING(3,"A haver");

    private int id;
    private String description;

    MethodPayment(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }


}
