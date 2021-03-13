package com.github.grusnac.taco.cloud.design;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class TacoViewConverter implements Converter<TacoView, TacoEntity> {

    private final IngredientEntityConverter ingredientEntityConverter;

    public TacoViewConverter(IngredientEntityConverter ingredientEntityConverter) {
        this.ingredientEntityConverter = ingredientEntityConverter;
    }

    @Override
    public TacoEntity convert(TacoView tacoView) {
        final TacoEntity tacoEntity = new TacoEntity();
        tacoEntity.setName(tacoView.name);
        tacoEntity.setIngredients(tacoView.ingredients.stream().map(ingredientEntityConverter::convert).collect(toList()));
        return tacoEntity;
    }
}
