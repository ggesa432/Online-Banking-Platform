package com.synergisticit.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/10/2024
 */
@Embeddable
public class Address {
    @NotEmpty
    private String addressLine1;
    @NotEmpty
    private String addressLine2;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String country;
    @NotEmpty
    private String phoneNumber;

    public @NotEmpty String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(@NotEmpty String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public @NotEmpty String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(@NotEmpty String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public @NotEmpty String getCity() {
        return city;
    }

    public void setCity(@NotEmpty String city) {
        this.city = city;
    }

    public @NotEmpty String getState() {
        return state;
    }

    public void setState(@NotEmpty String state) {
        this.state = state;
    }

    public @NotEmpty String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotEmpty String zipCode) {
        this.zipCode = zipCode;
    }

    public @NotEmpty String getCountry() {
        return country;
    }

    public void setCountry(@NotEmpty String country) {
        this.country = country;
    }

    public @NotEmpty String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
