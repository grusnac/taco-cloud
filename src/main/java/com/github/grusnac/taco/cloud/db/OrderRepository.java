package com.github.grusnac.taco.cloud.db;

import com.github.grusnac.taco.cloud.order.Order;

public interface OrderRepository {

    Order save(Order order);

}
