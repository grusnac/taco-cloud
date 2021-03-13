package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.TacoViewConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class OrderViewConverter implements Converter<Order, OrderEntity> {

    private final TacoViewConverter tacoViewConverter;

    public OrderViewConverter(TacoViewConverter tacoViewConverter) {
        this.tacoViewConverter = tacoViewConverter;
    }

    @Override
    public OrderEntity convert(Order order) {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTacos(order.tacos.stream().map(tacoViewConverter::convert).collect(toList()));
        orderEntity.setCcExpiration(order.ccExpiration);
        orderEntity.setCcNumber(order.ccNumber);
        orderEntity.setCccVV(order.cccVV);
        orderEntity.setDeliveryCity(order.city);
        orderEntity.setDeliveryName(order.name);
        orderEntity.setDeliveryState(order.state);
        orderEntity.setDeliveryStreet(order.street);
        orderEntity.setDeliveryZip(order.zip);
        return orderEntity;
    }
}
