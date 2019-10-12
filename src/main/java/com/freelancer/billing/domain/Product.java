package com.freelancer.billing.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "PRODUCT_BARCODE")
    private String barcode;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "PURCHASE_PRICE")
    private Double purchasePrice;

    @Column(name = "SALE_PRICE")
    private Double salePrice;

    @Column(name = "PHOTO_URL")
    private String photoUrl;

    @Column(name = "STOCKS")
    private Long stocks;

    @Column(name = "ITBIS")
    private Double itbis;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "CATEGORY_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVIDER_ID")
    private Providers providers;

    public Product() {
    }

    public Product(String name, String brand, Double purchasePrice, Double salePrice, Long stocks, Double itbis, String description) {
        this.name = name;
        this.brand = brand;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.stocks = stocks;
        this.itbis = itbis;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Long getStocks() {
        return stocks;
    }

    public void setStocks(Long stocks) {
        this.stocks = stocks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", stocks=" + stocks +
                ", description='" + description + '\'' +
                '}';
    }

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }
}
