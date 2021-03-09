package com.github.grusnac.taco.cloud.design;

import java.util.Collection;

public interface IngredientRepository {
    Collection<IngredientEntity> findAll();

    IngredientEntity find(String id);

    IngredientEntity save(IngredientEntity ingredientEntity);
}
