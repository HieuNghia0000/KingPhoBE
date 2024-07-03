package org.application.kingphobe.service;

import org.application.kingphobe.dto.UserFavouriteDTO;

import java.util.List;

public interface UserFavouriteService {
    UserFavouriteDTO saveUserFavourite(UserFavouriteDTO userFavoriteDTO);
    List<UserFavouriteDTO> getUserFavourites(Integer userId);
    UserFavouriteDTO getUserFavourite(Integer userId, Integer productId);
    void deleteUserFavourite(Integer id);
}
