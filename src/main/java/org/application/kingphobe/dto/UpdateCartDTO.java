package org.application.kingphobe.dto;

import lombok.Data;

@Data
public class UpdateCartDTO {
    private int userId;
    private int productId;
    private int quantity;
}
