package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.application.kingphobe.model.CartItem;
import org.application.kingphobe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    @Operation(summary = "Add product to cart")
    public ResponseEntity<Void> addToCart(@RequestBody CartItem cartItem) {
        cartService.addToCart(cartItem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart/{userId}")
    @Operation(summary = "Get cart items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        if (cartItems != null) {
            return ResponseEntity.ok(cartItems);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cart/{userId}")
    @Operation(summary = "Update cart")
    public ResponseEntity<Void> updateCartItem(@PathVariable int userId, @RequestBody CartItem cartItem) {
        cartService.updateCartItem(userId, cartItem);
        return ResponseEntity.ok().build();
    }
}