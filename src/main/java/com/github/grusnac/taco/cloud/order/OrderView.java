package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.TacoView;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderView {

    @Valid
    @NotNull
    public AddressView address;
    @CreditCardNumber(message = "Not a valid credit card number") //valid PAN 9067739166961106
    public String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$", message = "Must be formatted MM/YY")
    public String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    public String ccCvv;
    public List<TacoView> tacos = new ArrayList<>();

    @Override
    public String toString() {
        return "OrderView {" +
                "address='" + address + "', " +
                "ccNumber='" + ccNumber + "', " +
                "ccExpiration='" + ccExpiration + "', " +
                "ccCvv='" + ccCvv + "', " +
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
        OrderView orderView = (OrderView) o;
        return Objects.equals(address, orderView.address)
                && Objects.equals(ccNumber, orderView.ccNumber)
                && Objects.equals(ccExpiration, orderView.ccExpiration)
                && Objects.equals(ccCvv, orderView.ccCvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, ccNumber, ccExpiration, ccCvv);
    }

    public AddressView getAddress() {
        return address;
    }

    public void setAddress(AddressView address) {
        this.address = address;
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

    public void setCcCvv(String ccCvv) {
        this.ccCvv = ccCvv;
    }

    public List<TacoView> getTacos() {
        return tacos;
    }

    public void setTacos(List<TacoView> tacos) {
        this.tacos = tacos;
    }

    public void addDesign(TacoView tacos) {
        this.tacos.add(tacos);
    }

    public static class AddressView {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AddressView that = (AddressView) o;
            return Objects.equals(getName(), that.getName())
                    && Objects.equals(getStreet(), that.getStreet())
                    && Objects.equals(getCity(), that.getCity())
                    && Objects.equals(getState(), that.getState())
                    && Objects.equals(getZip(), that.getZip());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getStreet(), getCity(), getState(), getZip());
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
    }
}
