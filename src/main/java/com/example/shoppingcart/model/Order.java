// filepath: c:\genAI\ShoppingCart\shopping-cart-app\src\main\java\com\example\shoppingcart\model\Order.java
package com.example.shoppingcart.model;

import java.util.List;

public class Order {
    private String orderId;
    private List<Product> products;
    private ShippingInfo shippingInfo;

    public Order() {}

    public Order(String orderId, List<Product> products, ShippingInfo shippingInfo) {
        this.orderId = orderId;
        this.products = products;
        this.shippingInfo = shippingInfo;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public ShippingInfo getShippingInfo() { return shippingInfo; }
    public void setShippingInfo(ShippingInfo shippingInfo) { this.shippingInfo = shippingInfo; }

    public double getTotalPrice() {
        if (products == null) return 0.0;
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}