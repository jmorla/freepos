package com.freelancer.billing.model;

import java.util.Date;

public class CustomerDTO {

    private String firstName;

    private String lastName;

    private String socialId;

    private String email;

    private String phoneNumber;

    private Date birthDate;

    private Gender gender;

    private String city;

    private String postalCode;

    private int provinceId;

    private int countryId;

    private String addressLine1;

    private String addressLine2;

    private boolean taxExemption;

    private boolean applyDiscount;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
        return "CustomerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialId='" + socialId + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", provinceId=" + provinceId +
                ", countryId=" + countryId +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", taxExemption=" + taxExemption +
                ", applyDiscount=" + applyDiscount +
                '}';
    }
}
