package com.freelancer.billing.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    @Column(name = "CATEGORY_NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
