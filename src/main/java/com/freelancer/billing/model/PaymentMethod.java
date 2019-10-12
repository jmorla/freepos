package com.freelancer.billing.model;

public enum PaymentMethod {
    CASH(1), CREDIT_CARD(2), CHEQUE(3);

    private int value;

    PaymentMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
