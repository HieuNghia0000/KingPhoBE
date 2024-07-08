package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.application.kingphobe.dto.OrderDetailDTO;
import org.application.kingphobe.model.OrderDetail;
import org.application.kingphobe.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order-details", produces = "application/json")
@Tag(name = "Order Details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    @Operation(summary = "Get all order details")
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetail();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order detail by ID")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable int id) {
        return orderDetailService.getOrderDetailById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new order detail")
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetail createdOrderDetail = orderDetailService.createOrderDetail(orderDetailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order detail")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Integer id, @RequestBody OrderDetailDTO orderDetailDTO) {
        return orderDetailService.updateOrderDetail(id, orderDetailDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order detail")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}

