package org.application.kingphobe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {

    private int id;
    private UserDTO user;
    private LocalDateTime order_date;
    private Double total_amount;
}