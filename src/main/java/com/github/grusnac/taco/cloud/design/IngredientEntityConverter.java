package com.github.grusnac.taco.cloud.design;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientEntityConverter implements Converter<String, IngredientEntity> {

    private final IngredientRepository ingredientRepository;

    public IngredientEntityConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientEntity convert(String ingredientId) {
        return ingredientRepository.findById(ingredientId).orElse(null);
    }
}
