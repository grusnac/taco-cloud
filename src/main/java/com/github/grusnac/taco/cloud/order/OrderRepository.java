package com.github.grusnac.taco.cloud.order;

import com.github.grusnac.taco.cloud.order.OrderEntity;

public interface OrderRepository {

    OrderEntity save(OrderEntity order);

}
