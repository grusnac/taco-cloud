package com.github.grusnac.taco.cloud.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.grusnac.taco.cloud.design.TacoEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInserter;
    private final SimpleJdbcInsert orderTacoInserter;
    private final ObjectMapper objectMapper;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }

    public OrderEntity save(OrderEntity order) {
        order.setPlacedAt(ZonedDateTime.now(ZoneOffset.UTC));
        order.setDeliveryDate(ZonedDateTime.now(ZoneOffset.UTC));
        final long orderId = saveOrderDetails(order);
        order.setId(orderId);
        final List<TacoEntity> tacos = order.getTacos();
        for (TacoEntity taco : tacos) {
            saveToOrder(taco, orderId);
        }
        return order;
    }

    private long saveOrderDetails(OrderEntity order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        values.put("deliveryDate", order.getPlacedAt());
        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void saveToOrder(TacoEntity taco, long orderId) {
        final Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }

}
