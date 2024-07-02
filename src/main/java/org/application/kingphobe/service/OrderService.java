package org.application.kingphobe.service;

import org.application.kingphobe.dto.OrderDTO;
import org.application.kingphobe.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrder();

    Optional<Order> getOrderById(int id);

    Order createOrder(OrderDTO orderDTO);

    Optional<Order> updateOrder(Integer id, OrderDTO orderDTO);

    void deleteOrder(int id);
}
