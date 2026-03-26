package com.example.shoppingcart.storage;

import com.example.shoppingcart.model.Order;
import com.example.shoppingcart.model.Product;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileStorageService {

    private final String userFilePath = "data/users.txt";
    private final String productFilePath = "data/products.txt";
    private final String orderFilePath = "data/orders.txt";

    public void saveUser(String userData) throws IOException {
        writeToFile(userFilePath, userData);
    }

    public List<String> loadUsers() throws IOException {
        return readFromFile(userFilePath);
    }

    public void saveProduct(String productData) throws IOException {
        writeToFile(productFilePath, productData);
    }

    public List<String> loadProducts() throws IOException {
        return readFromFile(productFilePath);
    }

    public void saveOrder(Order order) throws IOException {
        // Simple serialization for demo: orderId|productIds|address,city,postalCode
        StringBuilder sb = new StringBuilder();
        sb.append(order.getOrderId()).append("|");
        if (order.getProducts() != null) {
            for (Product p : order.getProducts()) {
                sb.append(p.getId()).append(",");
            }
            if (!order.getProducts().isEmpty()) {
                sb.setLength(sb.length() - 1); // remove last comma
            }
        }
        sb.append("|");
        if (order.getShippingInfo() != null) {
            sb.append(order.getShippingInfo().getAddress()).append(",")
              .append(order.getShippingInfo().getCity()).append(",")
              .append(order.getShippingInfo().getPostalCode());
        }
        writeToFile(orderFilePath, sb.toString());
    }

    public List<String> loadOrders() throws IOException {
        return readFromFile(orderFilePath);
    }

    private void writeToFile(String filePath, String data) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
            writer.newLine();
        }
    }

    private List<String> readFromFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public List<Product> readProductsFromFile() {
        // For demo, return hardcoded products. Replace with file logic if needed.
        return Arrays.asList(
            new Product(1, "Apple", 1.5, "Fresh red apple"),
            new Product(2, "Banana", 1.0, "Organic yellow banana"),
            new Product(3, "Orange", 2.0, "Juicy orange orange")
        );
    }
}