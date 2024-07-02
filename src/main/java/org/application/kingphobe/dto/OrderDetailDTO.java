package org.application.kingphobe.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {

    private int id;
    private OrderDTO order;
    private ProductDTO product;
    private int quantity;
    private double price;
}
