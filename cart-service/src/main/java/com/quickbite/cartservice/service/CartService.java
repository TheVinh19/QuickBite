package com.quickbite.cartservice.service;

import com.quickbite.cartservice.dto.AddToCartRequest;
import com.quickbite.cartservice.entity.Cart;
import com.quickbite.cartservice.entity.CartItem;
import com.quickbite.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional(readOnly = true)
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> new Cart(userId));
    }

    @Transactional
    public Cart addItemToCart(Long userId, AddToCartRequest request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> new Cart(userId));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(request.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            item.setPrice(request.getPrice());
        } else {
            CartItem newItem = new CartItem(
                    request.getProductId(),
                    request.getProductName(),
                    request.getPrice(),
                    request.getQuantity()
            );
            cart.addItem(newItem);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeItemFromCart(Long userId, Long itemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        cart.getItems().removeIf(item -> item.getId() != null && item.getId().equals(itemId));
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.findByUserId(userId).ifPresent(cart -> {
            cart.getItems().clear();
            cartRepository.save(cart);
        });
    }
}
