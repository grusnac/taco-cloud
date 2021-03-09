package com.github.grusnac.taco.cloud.db;

import com.github.grusnac.taco.cloud.design.Ingredient;
import com.github.grusnac.taco.cloud.design.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        final long tacoId = saveTacoDetails(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoDetails(Taco taco) {
        taco.setPlacedAt(ZonedDateTime.now(ZoneOffset.UTC));
        PreparedStatementCreatorFactory preparedStatementCreatorFactory =  new PreparedStatementCreatorFactory("insert into Taco (name, placedAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        final PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory
                .newPreparedStatementCreator(List.of(taco.getName(), new Timestamp(taco.getPlacedAt().toInstant().toEpochMilli())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbcTemplate.update("insert into TacoIngredients (taco, ingredient) values (?, ?)",
                tacoId, ingredient.getId());
    }
}
