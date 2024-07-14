package org.application.kingphobe.service;

import org.application.kingphobe.model.CartItem;

import java.util.List;

public interface CartService {
    void addToCart(CartItem cartItem);
    List<CartItem> getCartItems(int userId);
    void updateCartItem(int userId, CartItem cartItem);
}
