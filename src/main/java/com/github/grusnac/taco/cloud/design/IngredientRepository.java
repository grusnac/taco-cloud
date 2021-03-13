package com.github.grusnac.taco.cloud.design;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, String> {

}
