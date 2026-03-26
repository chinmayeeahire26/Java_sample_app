package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Cart;
import com.example.shoppingcart.model.Order;
import com.example.shoppingcart.model.ShippingInfo;
import com.example.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public String submitOrder(
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("zip") String zip,
            HttpSession session,
            Model model) {
        // Get cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            model.addAttribute("message", "Your cart is empty. Please add items before submitting an order.");
            return "error";
        }
        // Build ShippingInfo and Order
        ShippingInfo shippingInfo = new ShippingInfo(address, city, zip);
        Order order = new Order();
        order.setProducts(cart.getItems());
        order.setShippingInfo(shippingInfo);
        // Save order and get orderId
        String orderId = orderService.submitOrder(order);
        order.setOrderId(orderId);
        // Add order details to model for confirmation page
        model.addAttribute("order", order);
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderTotal", cart.getTotalPrice());
        // Clear cart
        session.setAttribute("cart", new Cart());
        return "order_confirmation";
    }
}