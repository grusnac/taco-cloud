package com.github.grusnac.taco.cloud.design;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class TacoViewConverter implements Converter<Taco, TacoEntity> {

    private final IngredientConverter ingredientConverter;

    public TacoViewConverter(IngredientConverter ingredientConverter) {
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public TacoEntity convert(Taco taco) {
        final TacoEntity tacoEntity = new TacoEntity();
        tacoEntity.setName(taco.name);
        tacoEntity.setIngredients(taco.ingredients.stream().map(ingredientConverter::convert).collect(toList()));
        return tacoEntity;
    }
}
