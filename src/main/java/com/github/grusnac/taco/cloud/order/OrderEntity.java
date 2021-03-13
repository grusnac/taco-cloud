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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String ccCvv;
    @ManyToMany(targetEntity = TacoEntity.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Taco_Order_Tacos",
            joinColumns = @JoinColumn(name = "tacoOrder"),
            inverseJoinColumns = @JoinColumn(name = "taco"))
    private List<TacoEntity> tacos = new ArrayList<>();

    @PrePersist
    void placedAt() {
        this.setPlacedAt(ZonedDateTime.now(ZoneOffset.UTC));
        this.setDeliveryDate(ZonedDateTime.now(ZoneOffset.UTC));
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
        return getId() == that.getId()
                && Objects.equals(getPlacedAt(), that.getPlacedAt())
                && Objects.equals(getDeliveryDate(), that.getDeliveryDate())
                && Objects.equals(getDeliveryName(), that.getDeliveryName())
                && Objects.equals(getDeliveryStreet(), that.getDeliveryStreet())
                && Objects.equals(getDeliveryCity(), that.getDeliveryCity())
                && Objects.equals(getDeliveryState(), that.getDeliveryState())
                && Objects.equals(getDeliveryZip(), that.getDeliveryZip())
                && Objects.equals(getCcNumber(), that.getCcNumber())
                && Objects.equals(getCcExpiration(), that.getCcExpiration())
                && Objects.equals(getCcCvv(), that.getCcCvv())
                && Objects.equals(getTacos(), that.getTacos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlacedAt(), getDeliveryDate(), getDeliveryName(), getDeliveryStreet(),
                getDeliveryCity(), getDeliveryState(), getDeliveryZip(), getCcNumber(), getCcExpiration(),
                getCcCvv(), getTacos());
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

    public String getCcCvv() {
        return ccCvv;
    }

    public void setCcCvv(String cccVV) {
        this.ccCvv = cccVV;
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
