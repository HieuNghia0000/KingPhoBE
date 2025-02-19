package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.application.kingphobe.dto.CartDTO;
import org.application.kingphobe.dto.UpdateCartDTO;
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
    public ResponseEntity<Void> addToCart(@RequestBody CartDTO cartDTO) {
        cartService.addToCart(cartDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart/{userId}")
    @Operation(summary = "Get cart items")
    public ResponseEntity<List<CartDTO>> getCartItems(@PathVariable int userId) {
        List<CartDTO> cartDTOs = cartService.getCartItems(userId);
        if (!cartDTOs.isEmpty()) {
            return ResponseEntity.ok(cartDTOs);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cart/{cartId}")
    @Operation(summary = "Update cart")
    public ResponseEntity<Void> updateCartItem(@PathVariable int cartId, @RequestBody CartDTO cartDTO) {
        cartService.updateCartItem(cartId, cartDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cart/user/{userId}/product/{productId}")
    @Operation(summary = "Update cart by userId and productId")
    public ResponseEntity<Void> updateCartItemByUserIdAndProductId(@PathVariable int userId, @PathVariable int productId, @RequestBody UpdateCartDTO updateCartDTO) {
        cartService.updateCartItemByUserIdAndProductId(userId, productId, updateCartDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cart/{cartId}")
    @Operation(summary = "Delete cart item")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int cartId) {
        cartService.deleteCartItem(cartId);
        return ResponseEntity.ok().build();
    }
}