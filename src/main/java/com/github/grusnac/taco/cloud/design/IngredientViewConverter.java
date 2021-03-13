package com.github.grusnac.taco.cloud.design;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientViewConverter implements Converter<IngredientEntity, IngredientView> {

    @Override
    public IngredientView convert(IngredientEntity ingredientEntity) {
        return new IngredientView(ingredientEntity.getId(), ingredientEntity.getName(),
                ingredientEntity.getType().name());
    }
}
