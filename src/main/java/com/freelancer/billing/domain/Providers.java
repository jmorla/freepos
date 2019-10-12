package com.freelancer.billing.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROVIDERS")
public class Providers implements Serializable {

    @Id
    @Column(name = "PROVIDER_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String providerId;

    @Column(name = "PROVIDER_NAME")
    private String providerName;

    @Column(name = "RNC_SOCIALID")
    private String rncSocialId;

    @Column(name = "EMAIL")
    private String email;
    
    @JoinColumn(name = "LOCATION")
    @OneToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToMany(mappedBy = "providers", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    public Providers() {
    }

    

    public Providers(String providerId, String providerName, String rncSocialId, String email, Location location,
			Set<Product> products) {
		super();
		this.providerId = providerId;
		this.providerName = providerName;
		this.rncSocialId = rncSocialId;
		this.email = email;
		this.location = location;
		this.products = products;
	}



	public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    
    


    public String getProviderName() {
		return providerName;
	}



	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}



	public String getRncSocialId() {
		return rncSocialId;
	}



	public void setRncSocialId(String rncSocialId) {
		this.rncSocialId = rncSocialId;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}



	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}