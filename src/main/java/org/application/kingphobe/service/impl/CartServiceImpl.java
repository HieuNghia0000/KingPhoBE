package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.CartDTO;
import org.application.kingphobe.dto.UpdateCartDTO;
import org.application.kingphobe.model.Cart;
import org.application.kingphobe.model.Product;
import org.application.kingphobe.model.User;
import org.application.kingphobe.repository.CartRepository;
import org.application.kingphobe.repository.ProductRepository;
import org.application.kingphobe.repository.UserRepository;
import org.application.kingphobe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addToCart(CartDTO cartDTO) {
        User user = userRepository.findById(cartDTO.getUserId()).orElse(new User());
        Product product = productRepository.findById(cartDTO.getProductId()).orElse(new Product());

        Cart cart = cartRepository.findByUserAndProduct(user, product);
        if (cart == null) {
            cart = Cart.builder()
                    .user(user)
                    .product(product)
                    .quantity(cartDTO.getQuantity())
                    .createdAt(new Date())
                    .build();
        } else {
            cart.setQuantity(cart.getQuantity() + cartDTO.getQuantity());
        }
        cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getCartItems(int userId) {
        List<Cart> carts = cartRepository.findByUser_UserId(userId);
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : carts) {
            cartDTOs.add(CartDTO.builder()
                    .cartId(cart.getCartId())
                    .userId(cart.getUser().getUserId())
                    .productId(cart.getProduct().getProductId())
                    .quantity(cart.getQuantity())
                    .createdAt(cart.getCreatedAt())
                    .build());
        }
        return cartDTOs;
    }

    @Override
    public void updateCartItem(int cartId, CartDTO cartDTO) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.setQuantity(cartDTO.getQuantity());
            cartRepository.save(cart);
        }
    }

    @Override
    public void updateCartItemByUserIdAndProductId(int userId, int productId, UpdateCartDTO updateCartDTO) {
        Cart cart = cartRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
        if (cart != null) {
            cart.setQuantity(updateCartDTO.getQuantity());
            cartRepository.save(cart);
        }
    }

    @Override
    public void deleteCartItem(int cartId) {
        cartRepository.deleteById(cartId);
    }
}
