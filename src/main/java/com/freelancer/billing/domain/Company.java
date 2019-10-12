package com.freelancer.billing.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "COMPANIES")
public class Company {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "COMPANY_NAME")
    private String name;

    @Column(name = "RNC")
    private String rnc;

    @Column(name = "SLOGAN")
    private String slogan;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "WEB_SITE")
    private String website;

    @Column(name = "LOGO_URL")
    private String logoUrl;

    @JoinColumn(name = "ACCOUNT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @JoinColumn(name = "LOCATION_ID")
    @OneToOne
    private Location location;


}
