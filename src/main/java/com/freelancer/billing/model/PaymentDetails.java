package com.freelancer.billing.model;

import com.freelancer.billing.domain.Customer;

public class PaymentDetails {

    private String customerName;

    private String customerId;

    private Integer itemsCount;

    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    private Double total;

    private Double paid;

    private Double change;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
}
