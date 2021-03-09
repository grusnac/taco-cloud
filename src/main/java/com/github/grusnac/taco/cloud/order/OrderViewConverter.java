package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.design.TacoViewConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class OrderViewConverter implements Converter<OrderView, OrderEntity> {

    private final TacoViewConverter tacoViewConverter;

    public OrderViewConverter(TacoViewConverter tacoViewConverter) {
        this.tacoViewConverter = tacoViewConverter;
    }

    @Override
    public OrderEntity convert(OrderView orderView) {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTacos(orderView.tacos.stream().map(tacoViewConverter::convert).collect(toList()));
        orderEntity.setCcExpiration(orderView.ccExpiration);
        orderEntity.setCcNumber(orderView.ccNumber);
        orderEntity.setCccVV(orderView.cccVV);
        orderEntity.setDeliveryCity(orderView.city);
        orderEntity.setDeliveryName(orderView.name);
        orderEntity.setDeliveryState(orderView.state);
        orderEntity.setDeliveryStreet(orderView.street);
        orderEntity.setDeliveryZip(orderView.zip);
        return orderEntity;
    }
}
