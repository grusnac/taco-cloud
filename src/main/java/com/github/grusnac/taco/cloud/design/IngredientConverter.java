package com.github.grusnac.taco.cloud.design;

import com.github.grusnac.taco.cloud.db.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    public IngredientConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String ingredientId) {
        return ingredientRepo.find(ingredientId);
    }
}
