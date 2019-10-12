package com.freelancer.billing.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    private Integer id;

    @Column(name = "COUNTRY_NAME")
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Province> provinces = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }

    @Override
    public String toString() {
        return name;
    }
}
