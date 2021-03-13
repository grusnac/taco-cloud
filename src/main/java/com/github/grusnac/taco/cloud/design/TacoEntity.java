package com.github.grusnac.taco.cloud.design;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Taco")
@Table(name = "Taco")
public class TacoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private ZonedDateTime placedAt;
    @ManyToMany(targetEntity = IngredientEntity.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Taco_Ingredients", joinColumns = @JoinColumn(name = "taco"), inverseJoinColumns = @JoinColumn(name = "ingredient"))
    private List<IngredientEntity> ingredientEntities = new ArrayList<>();

    protected TacoEntity() {
    }

    public TacoEntity(long id, String name, ZonedDateTime placedAt, List<IngredientEntity> ingredientEntities) {
        this.id = id;
        this.name = name;
        this.placedAt = placedAt;
        this.ingredientEntities = ingredientEntities;
    }

    void createdAt() {
        this.setPlacedAt(ZonedDateTime.now(ZoneOffset.UTC));
    }

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