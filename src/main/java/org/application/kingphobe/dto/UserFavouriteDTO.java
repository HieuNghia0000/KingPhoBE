package org.application.kingphobe.dto;

import lombok.Data;

@Data
public class UserFavouriteDTO {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private boolean isFavorite;
}
