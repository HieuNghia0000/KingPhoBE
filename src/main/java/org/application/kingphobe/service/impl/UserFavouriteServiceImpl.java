package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.UserFavouriteDTO;
import org.application.kingphobe.model.Product;
import org.application.kingphobe.model.User;
import org.application.kingphobe.model.UserFavourites;
import org.application.kingphobe.repository.ProductRepository;
import org.application.kingphobe.repository.UserFavouriteRepository;
import org.application.kingphobe.repository.UserRepository;
import org.application.kingphobe.service.UserFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFavouriteServiceImpl implements UserFavouriteService {

    @Autowired
    private UserFavouriteRepository userFavouriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public UserFavouriteDTO saveUserFavourite(UserFavouriteDTO userFavouriteDTO) {
        UserFavourites userFavourites = convertToEntity(userFavouriteDTO);
        userFavourites = userFavouriteRepository.save(userFavourites);
        return convertToDTO(userFavourites);
    }

    @Override
    public List<UserFavouriteDTO> getUserFavourites(Integer userId) {
        return userFavouriteRepository.findByUserUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserFavouriteDTO getUserFavourite(Integer userId, Integer productId) {
        UserFavourites userFavourites = userFavouriteRepository.findByUserUserIdAndProductProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Favourite not found"));
        return convertToDTO(userFavourites);
    }

    @Override
    public void deleteUserFavourite(Integer id) {
        userFavouriteRepository.deleteById(id);
    }

    private UserFavourites convertToEntity(UserFavouriteDTO userFavouriteDTO) {
        UserFavourites userFavourites = new UserFavourites();
        userFavourites.setId(userFavouriteDTO.getId());

        // Fetch the User entity from the database
        User user = userRepository.findById(userFavouriteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userFavourites.setUser(user);

        // Fetch the Product entity from the database
        Product product = productRepository.findById(userFavouriteDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        userFavourites.setProduct(product);

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
