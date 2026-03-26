package com.example.shoppingcart.service;

import com.example.shoppingcart.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private Map<String, String> users = new HashMap<>();

    public AuthService() {
        // Sample users for demonstration
        users.put("admin", "admin123");
        users.put("user", "user123");
    }

    public boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public User getUser(String username) {
        if (users.containsKey(username)) {
            return new User(username, users.get(username));
        }
        return null;
    }
}