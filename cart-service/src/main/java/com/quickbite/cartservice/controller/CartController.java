package com.quickbite.cartservice.controller;

import com.quickbite.cartservice.dto.AddToCartRequest;
import com.quickbite.cartservice.entity.Cart;
import com.quickbite.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Cart> addItem(@PathVariable Long userId, @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, request));
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Cart> removeItem(@PathVariable Long userId, @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, itemId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, String>> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok(Map.of("message", "Cart cleared successfully"));
    }
}
