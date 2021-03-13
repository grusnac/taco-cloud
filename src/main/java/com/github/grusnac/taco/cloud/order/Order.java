package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.Taco;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    @NotBlank(message = "Name is required")
    public String name;
    @NotBlank(message = "Street is required")
    public String street;
    @NotBlank(message = "City is required")
    public String city;
    @NotBlank(message = "State is required")
    public String state;
    @NotBlank(message = "Zip code is required")
    public String zip;
    @CreditCardNumber(message = "Not a valid credit card number") //valid PAN 9067739166961106
    public String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$", message = "Must be formatted MM/YY")
    public String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    public String cccVV;
    public List<Taco> tacos = new ArrayList<>();

    @Override
    public String toString() {
        return "Order {" +
                "deliveryName='" + name +  "', " +
                "deliveryStreet='" + street +  "', " +
                "deliveryCity='" + city +  "', " +
                "deliverySate='" + state +  "', " +
                "deliveryZip='" + zip +  "', " +
                "ccNumber='" + ccNumber +  "', " +
                "ccExpiration='" + ccExpiration +  "', " +
                "cccVV='" + cccVV +  "', " +
                "tacos=" + tacos +
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
        return Objects.equals(name, order.name)
                && Objects.equals(street, order.street)
                && Objects.equals(city, order.city)
                && Objects.equals(state, order.state)
                && Objects.equals(zip, order.zip)
                && Objects.equals(ccNumber, order.ccNumber)
                && Objects.equals(ccExpiration, order.ccExpiration)
                && Objects.equals(cccVV, order.cccVV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, city, state, zip, ccNumber, ccExpiration, cccVV);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCccVV() {
        return cccVV;
    }

    public void setCccVV(String cccVV) {
        this.cccVV = cccVV;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public void addDesign(Taco tacos) {
        this.tacos.add(tacos);
    }
}
