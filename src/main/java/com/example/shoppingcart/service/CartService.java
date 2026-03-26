package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Cart;
import com.example.shoppingcart.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private Cart cart;

    public CartService() {
        this.cart = new Cart();
    }

    public void addProduct(Product product) {
        cart.getItems().add(product);
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
    }

    public Cart getCart() {
        return cart;
    }

    public void clearCart() {
        cart.getItems().clear();
        cart.setTotalPrice(0);
    }
}