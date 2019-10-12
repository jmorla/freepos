package com.freelancer.billing.domain;

import com.freelancer.billing.domain.Product;

import javax.persistence.*;

@Entity
@Table(name = "INVOICE_LINES")
public class Item {

    @Id
    @Column(name = "INVOICE_LINE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    private Double itbis;

    private Integer quantity;

    private Double total;

    @Transient
    private String productId;

    @Transient
    private String barcode;

    @Transient
    private String name;

    @Transient
    private Long stock;


    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Item() {
    }

    public Item(String productId, String barcode, String name, Double price, Double itbis, Integer quantity, Long stock, Double total, Product product) {
        this.productId = productId;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.itbis = itbis;
        this.stock = stock;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
