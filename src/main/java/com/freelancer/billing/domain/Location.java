package com.freelancer.billing.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "LOCATIONS")
public class Location {

    @Id
    @Column(name = "LOCATION_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "STREET_1")
    private String street1;

    @Column(name = "STREET_2")
    private String street2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;

    @OneToOne(mappedBy = "location", fetch = FetchType.LAZY)
    private Customer customer;
    
    @OneToOne(mappedBy = "location", fetch = FetchType.LAZY)
    private Providers provider;

    public Location() {
    }

    public Location(String street1, String street2, String city, String postalCode, Province province) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	public Providers getProvider() {
		return provider;
	}

	public void setProvider(Providers provider) {
		this.provider = provider;
	}

	

	
    
    
}
