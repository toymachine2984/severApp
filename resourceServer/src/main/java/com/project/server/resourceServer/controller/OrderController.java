package com.project.server.resourceServer.controller;


import com.project.server.resourceServer.entity.Order;
import com.project.server.resourceServer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Order> getOrders() {
        return orderService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order addOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
