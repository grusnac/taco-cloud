package com.github.grusnac.taco.cloud.order;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.*;
import java.util.Objects;

public class Order {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Zip code is required")
    private String zip;
    @CreditCardNumber(message = "Not a valid credit card number") //valid PAN 9067739166961106
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String cccVV;

    @Override
    public String toString() {
        return "Order {" +
                "name='" + name + "', " +
                "street='" + street + "', " +
                "city='" + city + "', " +
                "state='" + state + "', " +
                "zip='" + zip + "', " +
                "ccNumber='" + ccNumber + "', " +
                "ccExpiration='" + ccExpiration + "', " +
                "cccVV='" + cccVV +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getName(), order.getName()) 
                && Objects.equals(getStreet(), order.getStreet()) 
                && Objects.equals(getCity(), order.getCity()) 
                && Objects.equals(getState(), order.getState()) 
                && Objects.equals(getZip(), order.getZip()) 
                && Objects.equals(getCcNumber(), order.getCcNumber()) 
                && Objects.equals(getCcExpiration(), order.getCcExpiration()) 
                && Objects.equals(getCccVV(), order.getCccVV());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZip(), getCcNumber(), getCcExpiration(), getCccVV());
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public String getCccVV() {
        return cccVV;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public void setCccVV(String cccVV) {
        this.cccVV = cccVV;
    }
}
