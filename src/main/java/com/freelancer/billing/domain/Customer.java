package com.freelancer.billing.domain;

import com.freelancer.billing.model.Gender;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Formula(value = "concat(FIRST_NAME,' ',LAST_NAME)")
    private String fullName;

    @Column(name = "SOCIAL_ID", unique = true)
    private String socialId;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "CREATION_DATE")
    private LocalDateTime createAt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "TAX_EXEMPTION")
    private boolean taxExemption;

    @Column(name = "APPLY_DISCOUNT")
    private boolean applyDiscount;

    public Customer() {

    }

    public Customer(String id) {
        this.id = id;
    }

    public Customer(String firstName, String lastName, String socialId, String phoneNumber, String email,
                    Date birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialId = socialId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Customer(String firstName, String lastName, String socialId, String phoneNumber,
                    String email, Date birthDate, Gender gender,
                    boolean taxExemption, boolean applyDiscount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialId = socialId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.taxExemption = taxExemption;
        this.applyDiscount = applyDiscount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isTaxExemption() {
        return taxExemption;
    }

    public void setTaxExemption(boolean taxExemption) {
        this.taxExemption = taxExemption;
    }

    public boolean isApplyDiscount() {
        return applyDiscount;
    }

    public void setApplyDiscount(boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

    @Override
    public String toString() {
        return id;
    }


}
