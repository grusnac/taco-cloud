package com.github.grusnac.taco.cloud.design;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<IngredientEntity> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public IngredientEntity find(String id) {
        return jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient, id);
    }

    @Override
    public IngredientEntity save(IngredientEntity ingredientEntity) {
        jdbcTemplate.update("insert into Ingredient (id, name, type) values (?,?,?)",
                ingredientEntity.getId(),
                ingredientEntity.getName(),
                ingredientEntity.getType().name());
        return ingredientEntity;
    }

    private IngredientEntity mapRowToIngredient(ResultSet resultSet, int rowNumber) throws SQLException {
        return new IngredientEntity(
                resultSet.getString("id"),
                resultSet.getString("name"),
                IngredientEntity.Type.valueOf(resultSet.getString("type"))
        );
    }
}
