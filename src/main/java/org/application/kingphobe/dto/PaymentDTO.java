package org.application.kingphobe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {

    private int id;
    private OrderDTO order;
    private String paymentMethod;
    private Double amount;
    private LocalDateTime paymentDate;
}
