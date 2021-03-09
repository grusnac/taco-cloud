package com.github.grusnac.taco.cloud.design;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class Taco {

    private long id;
    private ZonedDateTime placedAt;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotEmpty(message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Taco taco = (Taco) o;
        return Objects.equals(getName(), taco.getName())
                && Objects.equals(getIngredients(), taco.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getIngredients());
    }

    @Override
    public String toString() {
        return "Taco {" +
                "name='" + name + "', " +
                "ingredients=" + ingredients +
                "}";
    }
}