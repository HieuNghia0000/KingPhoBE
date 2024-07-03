package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.UserFavouriteDTO;
import org.application.kingphobe.model.UserFavourites;
import org.application.kingphobe.repository.UserFavouriteRepository;
import org.application.kingphobe.service.UserFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFavouriteServiceImpl implements UserFavouriteService {

    @Autowired
    private UserFavouriteRepository userFavouriteRepository;

    @Override
    public UserFavouriteDTO saveUserFavourite(UserFavouriteDTO userFavouriteDTO) {
        UserFavourites userFavourites = convertToEntity(userFavouriteDTO);
        userFavourites = userFavouriteRepository.save(userFavourites);
        return convertToDTO(userFavourites);
    }

    @Override
    public List<UserFavouriteDTO> getUserFavourites(Integer userId) {
        return userFavouriteRepository.findAll().stream()
                .filter(fav -> fav.getUser().getUserId().equals(userId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserFavouriteDTO getUserFavourite(Integer userId, Integer productId) {
        UserFavourites userFavourites = userFavouriteRepository.findAll().stream()
                .filter(fav -> fav.getUser().getUserId().equals(userId) && fav.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        return convertToDTO(userFavourites);
    }

    @Override
    public void deleteUserFavourite(Integer id) {
        userFavouriteRepository.deleteById(id);
    }

    private UserFavourites convertToEntity(UserFavouriteDTO userFavouriteDTO) {
        UserFavourites userFavourites = new UserFavourites();
        userFavourites.setId(userFavouriteDTO.getId());
        // Assuming User and Product entities are already loaded from the database
        // and set to the userFavourite entity
        userFavourites.setFavorite(userFavouriteDTO.isFavorite());
        return userFavourites;
    }

    private UserFavouriteDTO convertToDTO(UserFavourites userFavourites) {
        UserFavouriteDTO userFavouriteDTO = new UserFavouriteDTO();
        userFavouriteDTO.setId(userFavourites.getId());
        userFavouriteDTO.setUserId(userFavourites.getUser().getUserId());
        userFavouriteDTO.setProductId(userFavourites.getProduct().getProductId());
        userFavouriteDTO.setFavorite(userFavourites.isFavorite());
        return userFavouriteDTO;
    }
}
