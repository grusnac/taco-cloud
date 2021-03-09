package com.github.grusnac.taco.cloud.db;

import com.github.grusnac.taco.cloud.design.Ingredient;

import java.util.Collection;

public interface IngredientRepository {
    Collection<Ingredient> findAll();

    Ingredient find(String id);

    Ingredient save(Ingredient ingredient);
}
