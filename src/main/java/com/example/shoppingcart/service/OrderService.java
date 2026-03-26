package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Order;
import com.example.shoppingcart.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class OrderService {

    private final FileStorageService fileStorageService;

    @Autowired
    public OrderService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public String submitOrder(Order order) {
        String orderId = generateOrderId();
        order.setOrderId(orderId);
        try {
            fileStorageService.saveOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return orderId;
    }

    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}