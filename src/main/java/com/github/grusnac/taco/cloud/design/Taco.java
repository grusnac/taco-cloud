package com.github.grusnac.taco.cloud.design;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Taco {
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotEmpty(message = "You must choose at least 1 ingredient")
    private List<String> ingredients;

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<String> ingredients) {
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