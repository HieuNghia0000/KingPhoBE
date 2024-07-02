package org.application.kingphobe.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InvoiceDTO {

    private int id;
    private OrderDTO order;
    private Date invoiceDate;
    private Double amount;
    private Date dueDate;
    private String status;
}
