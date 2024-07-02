package org.application.kingphobe.dto;

import lombok.Data;

@Data
public class PaymentMethodDTO {

    private int id;
    private String methodName;
    private String description;
}
