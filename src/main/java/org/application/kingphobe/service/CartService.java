package org.application.kingphobe.service;


import org.application.kingphobe.dto.CartDTO;

import java.util.List;

public interface CartService {
    void addToCart(CartDTO cartDTO);
    List<CartDTO> getCartItems(int userId);
    void updateCartItem(CartDTO cartDTO);
    void deleteCartItem(int cartId);
}
