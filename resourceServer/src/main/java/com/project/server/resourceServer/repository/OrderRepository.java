package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {



}
