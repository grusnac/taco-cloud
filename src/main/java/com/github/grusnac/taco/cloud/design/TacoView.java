package com.github.grusnac.taco.cloud.design;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TacoView {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    public String name;
    @NotEmpty(message = "You must choose at least 1 ingredient")
    public List<String> ingredients = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TacoView tacoView = (TacoView) o;
        return Objects.equals(name, tacoView.name)
                && Objects.equals(ingredients, tacoView.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }

    @Override
    public String toString() {
        return "TacoView {" +
                "name='" + name + "', " +
                "ingredients=" + ingredients +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}