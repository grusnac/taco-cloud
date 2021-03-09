package com.github.grusnac.taco.cloud.design;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter<String, IngredientEntity> {

    private IngredientRepository ingredientRepo;

    public IngredientConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientEntity convert(String ingredientId) {
        return ingredientRepo.find(ingredientId);
    }
}
