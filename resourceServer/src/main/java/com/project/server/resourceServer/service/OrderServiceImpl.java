package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Order;
import com.project.server.resourceServer.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
@Transactional("dataTransactionManager")
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Iterable<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
