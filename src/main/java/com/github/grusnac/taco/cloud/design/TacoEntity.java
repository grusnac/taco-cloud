package com.github.grusnac.taco.cloud.design;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TacoEntity {

    private long id;
    private String name;
    private ZonedDateTime placedAt;
    private List<IngredientEntity> ingredientEntities = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(ZonedDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredientEntities;
    }

    public void setIngredients(List<IngredientEntity> ingredientEntities) {
        this.ingredientEntities = ingredientEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TacoEntity taco = (TacoEntity) o;
        return Objects.equals(id, taco.id)
                && Objects.equals(name, taco.name)
                && Objects.equals(placedAt, taco.placedAt)
                && Objects.equals(ingredientEntities, taco.ingredientEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, placedAt, ingredientEntities);
    }

    @Override
    public String toString() {
        return "Taco {" +
                "id='" + id + "', " +
                "name='" + name + "', " +
                "placedAt=" + placedAt + "', " +
                "ingredients=" + ingredientEntities +
                "}";
    }
}