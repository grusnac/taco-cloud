package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.TacoEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderEntity {

    private long id;
    private ZonedDateTime placedAt;
    private ZonedDateTime deliveryDate;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String cccVV;
    private List<TacoEntity> tacos = new ArrayList<>();

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
        OrderEntity order = (OrderEntity) o;
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
        return Objects.hash(getId(), getPlacedAt(), getDeliveryDate(), getDeliveryName(), getDeliveryStreet(),
                getDeliveryCity(), getDeliveryState(), getDeliveryZip(), getCcNumber(), getCcExpiration(), getCccVV());
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

    public List<TacoEntity> getTacos() {
        return tacos;
    }

    public void setTacos(List<TacoEntity> tacos) {
        this.tacos = tacos;
    }

    public void addDesign(TacoEntity taco) {
        tacos.add(taco);
    }
}
