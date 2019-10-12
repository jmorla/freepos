package com.freelancer.billing.domain;

import com.freelancer.billing.model.PaymentMethod;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "INVOICES")
public class Invoice implements Serializable{

    @Id
    @Column(name = "INVOICE_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "INVOICE_DATE")
    private LocalDateTime createdAt;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateAt;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(optional = true)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
