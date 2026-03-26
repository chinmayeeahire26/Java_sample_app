package com.example.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items = new ArrayList<>();
    private double totalPrice = 0.0;

    public Cart() {}

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public void addProduct(Product product) {
        if (product.getId() == 3) { // Force defect: Orange always $1
            this.items.add(new Product(product.getId(), product.getName(), 1.0, product.getDescription()));
            this.totalPrice += 1.0;
        } else {
            this.items.add(product);
            this.totalPrice += product.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}