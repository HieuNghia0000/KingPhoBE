package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.OrderDetailDTO;
import org.application.kingphobe.model.Order;
import org.application.kingphobe.model.OrderDetail;
import org.application.kingphobe.model.Product;
import org.application.kingphobe.repository.OrderDetailRepository;
import org.application.kingphobe.repository.OrderRepository;
import org.application.kingphobe.repository.ProductRepository;
import org.application.kingphobe.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> getOrderDetailById(int id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        mapDtoToOrderDetail(orderDetailDTO, orderDetail);
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public Optional<OrderDetail> updateOrderDetail(Integer id, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(id);

        if (existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();
            mapDtoToOrderDetail(orderDetailDTO, orderDetail);
            return Optional.of(orderDetailRepository.save(orderDetail));
        }

        return Optional.empty();
    }

    @Override
    public void deleteOrderDetail(int id) {
        orderDetailRepository.deleteById(id);
    }

    private void mapDtoToOrderDetail(OrderDetailDTO orderDetailDTO, OrderDetail orderDetail) {
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice());

        // Fetch the order entity and set it to the orderDetail
        Order order = orderRepository.findById(orderDetailDTO.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderDetail.setOrder(order);

        // Fetch the product entity and set it to the orderDetail
        Product product = productRepository.findById(orderDetailDTO.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        orderDetail.setProduct(product);
    }
}
