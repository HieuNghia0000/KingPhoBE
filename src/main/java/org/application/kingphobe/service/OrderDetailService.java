package org.application.kingphobe.service;

import org.application.kingphobe.dto.OrderDetailDTO;
import org.application.kingphobe.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetail();

    Optional<OrderDetail> getOrderDetailById(int id);

    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO);

    Optional<OrderDetail> updateOrderDetail(Integer id, OrderDetailDTO orderDetailDTO);

    void deleteOrderDetail(int id);
}
