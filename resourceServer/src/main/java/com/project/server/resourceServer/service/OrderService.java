package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Order;

public interface OrderService {

    Iterable<Order> getAll();

    Order save(Order order);
}
