package com.github.grusnac.taco.cloud.design;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Ingredient")
@Table(name = "Ingredient")
public class IngredientEntity {

    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    protected IngredientEntity() {}

    public IngredientEntity(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IngredientEntity that = (IngredientEntity) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType());
    }

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
