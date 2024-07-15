package org.application.kingphobe.service;


import org.application.kingphobe.dto.CartDTO;
import org.application.kingphobe.dto.UpdateCartDTO;

import java.util.List;

public interface CartService {
    void addToCart(CartDTO cartDTO);
    List<CartDTO> getCartItems(int userId);
    void updateCartItem(int cartId, CartDTO cartDTO);
    void updateCartItemByUserIdAndProductId(int userId, int productId, UpdateCartDTO updateCartDTO);
    void deleteCartItem(int cartId);
}
