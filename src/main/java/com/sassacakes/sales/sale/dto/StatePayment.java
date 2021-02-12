package com.sassacakes.sales.sale.dto;

public enum StatePayment {

    PENDING(1, "Pendente"),
    PAID(2, "Pago"),
    CANCELLED(3, "Cancelado");

    private int code;
    private String description;

    StatePayment(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static StatePayment toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (StatePayment x : StatePayment.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }

}
