package org.application.kingphobe.service.impl;

import org.application.kingphobe.model.Cart;
import org.application.kingphobe.model.CartItem;
import org.application.kingphobe.repository.CartItemRepository;
import org.application.kingphobe.repository.CartRepository;
import org.application.kingphobe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void addToCart(CartItem cartItem) {
        Cart cart = cartRepository.findByUser_UserId(cartItem.getCart().getUser().getUserId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(cartItem.getCart().getUser());
            cartRepository.save(cart);
        }
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(int userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        if (cart != null) {
            return cartItemRepository.findByCart_CartId(cart.getCartId());
        }
        return null;
    }

    @Override
    public void updateCartItem(int userId, CartItem cartItem) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        if (cart != null) {
            List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cart.getCartId());
            for (CartItem existingCartItem : cartItems) {
                if (existingCartItem.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
                    existingCartItem.setQuantity(cartItem.getQuantity());
                    cartItemRepository.save(existingCartItem);
                    return;
                }
            }
        }
    }
}
