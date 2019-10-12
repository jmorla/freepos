package com.freelancer.billing.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROVINCES")
public class Province {

    @Id
    @Column(name = "PROVINCE_ID")
    private Integer id;

    @Column(name = "PROVINCE_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @OneToMany(mappedBy = "province")
    private Set<Location> locations = new HashSet<>();

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return name;
    }
}
