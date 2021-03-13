package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.TacoEntity;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Order")
@Table(name = "Taco_Order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToMany(targetEntity = TacoEntity.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Taco_Order_Tacos", joinColumns = @JoinColumn(name = "tacoOrder"), inverseJoinColumns = @JoinColumn(name = "taco"))
    private List<TacoEntity> tacos = new ArrayList<>();

    @PrePersist
    void placedAt() {
        this.setPlacedAt(ZonedDateTime.now(ZoneOffset.UTC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderEntity that = (OrderEntity) o;
        return id == that.id
                && Objects.equals(placedAt, that.placedAt)
                && Objects.equals(deliveryDate, that.deliveryDate)
                && Objects.equals(deliveryName, that.deliveryName)
                && Objects.equals(deliveryStreet, that.deliveryStreet)
                && Objects.equals(deliveryCity, that.deliveryCity)
                && Objects.equals(deliveryState, that.deliveryState)
                && Objects.equals(deliveryZip, that.deliveryZip)
                && Objects.equals(ccNumber, that.ccNumber)
                && Objects.equals(ccExpiration, that.ccExpiration)
                && Objects.equals(cccVV, that.cccVV)
                && Objects.equals(tacos, that.tacos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placedAt, deliveryDate, deliveryName, deliveryStreet, deliveryCity,
                deliveryState, deliveryZip, ccNumber, ccExpiration, cccVV, tacos);
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
