package org.application.kingphobe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private int cartId;
    private int userId;
    private int productId;
    private int quantity;
    private Date createdAt;
}
