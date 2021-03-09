package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.Taco;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private long id;
    private ZonedDateTime placedAt;
    private ZonedDateTime deliveryDate;
    @NotBlank(message = "Name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;
    @CreditCardNumber(message = "Not a valid credit card number") //valid PAN 9067739166961106
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String cccVV;
    private List<Taco> tacos = new ArrayList<>();

    @Override
    public String toString() {
        return "Order {" +
                "id=" + id + "', " +
                "placedAt=" + placedAt + "', " +
                "deliveryDate=" + deliveryDate + "', " +
                "deliveryName='" + deliveryName +  "', " +
                "deliveryStreet='" + deliveryStreet +  "', " +
                "deliveryCity='" + deliveryCity +  "', " +
                "deliverySate='" + deliveryState +  "', " +
                "deliveryZip='" + deliveryZip +  "', " +
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
        return Objects.equals(getDeliveryName(), order.getDeliveryName())
                && Objects.equals(getDeliveryDate(), order.getDeliveryDate())
                && Objects.equals(getPlacedAt(), order.getPlacedAt())
                && Objects.equals(getDeliveryStreet(), order.getDeliveryStreet())
                && Objects.equals(getDeliveryCity(), order.getDeliveryCity())
                && Objects.equals(getDeliveryState(), order.getDeliveryState())
                && Objects.equals(getDeliveryZip(), order.getDeliveryZip())
                && Objects.equals(getCcNumber(), order.getCcNumber())
                && Objects.equals(getCcExpiration(), order.getCcExpiration())
                && Objects.equals(getCccVV(), order.getCccVV());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeliveryName(), getDeliveryStreet(), getDeliveryCity(), getDeliveryState(), getDeliveryZip(), getCcNumber(), getCcExpiration(), getCccVV());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(ZonedDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
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

    public void addDesign(Taco taco) {
        tacos.add(taco);
    }
}
