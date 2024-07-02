package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.OrderDTO;
import org.application.kingphobe.model.Order;
import org.application.kingphobe.model.User;
import org.application.kingphobe.repository.OrderRepository;
import org.application.kingphobe.repository.UserRepository;
import org.application.kingphobe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        mapDtoToOrder(orderDTO, order);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> updateOrder(Integer id, OrderDTO orderDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);

        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            mapDtoToOrder(orderDTO, order);
            return Optional.of(orderRepository.save(order));
        }

        return Optional.empty();
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    private void mapDtoToOrder(OrderDTO orderDTO, Order order) {
        order.setOrderDate(Date.from(orderDTO.getOrder_date().atZone(ZoneId.systemDefault()).toInstant()));
        order.setTotalAmount(orderDTO.getTotal_amount());

        // Fetch the user entity and set it to the order
        User user = userRepository.findById(orderDTO.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
    }
}
