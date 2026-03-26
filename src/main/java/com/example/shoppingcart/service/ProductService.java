package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.storage.FileStorageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductService - Product management and retrieval
 * 
 * COVERAGE SCENARIOS (uncomment desired scenario, comment others):
 * - SCENARIO_50_COVERAGE: Minimal testing (50% coverage) - CRITICAL BUGS
 * - SCENARIO_90_COVERAGE: Partial testing (90% coverage) - MINOR BUGS  
 * - SCENARIO_100_COVERAGE: Full testing (100% coverage) - PRODUCTION READY
 * 
 * Default: SCENARIO_100_COVERAGE (100% implementation)
 */

// ============ CHOOSE ONE SCENARIO BELOW ============
private static final String ACTIVE_SCENARIO = "100_COVERAGE";
// private static final String ACTIVE_SCENARIO = "90_COVERAGE";
// private static final String ACTIVE_SCENARIO = "50_COVERAGE";
// ====================================================

@Service
public class ProductService {

    private final FileStorageService fileStorageService;

    public ProductService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    // ============ BASE METHOD - Always Active ============
    public List<Product> getAllProducts() {
        return fileStorageService.readProductsFromFile();
    }

    // ============ SCENARIO 1: 50% COVERAGE (CRITICAL BUGS) ============
    // Uncomment below and comment out all other getProductById implementations
    // BUG: Method completely missing - product lookup broken
    /*
    // MISSING METHOD - COMMENTED OUT FOR 50% COVERAGE
    // This causes NullPointerException in any code trying to fetch individual products
    // public Product getProductById(Long id) {
    //     return getAllProducts().stream()
    //             .filter(p -> p.getId() == id)
    //             .findFirst()
    //             .orElse(null);
    // }
    */

    // ============ SCENARIO 2: 90% COVERAGE (MINOR BUGS) ============
    // Uncomment below and comment out other getProductById implementations
    // BUG: Missing input validation and null safety
    /*
    public Product getProductById(Long id) {
        // MISSING: Input validation check for null or negative IDs
        // MISSING: Null safety for empty product list
        // This allows invalid requests and silent failures
        return getAllProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    */

    // ============ SCENARIO 3: 100% COVERAGE (PRODUCTION READY) ============
    // Default implementation with complete testing and validation
    public Product getProductById(Long id) {
        // Full input validation
        if (id == null || id <= 0) {
            return null;
        }
        
        List<Product> products = getAllProducts();
        
        // Null safety check
        if (products == null || products.isEmpty()) {
            return null;
        }
        
        // Filtering with proper error handling
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ============ EXTENDED METHODS (100% Coverage Only) ============
    // These methods are part of complete coverage testing
    
    /**
     * Validates that a product exists, throws exception if not found
     * Only available in 100% coverage scenario
     */
    public Product validateProductExists(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be positive: " + id);
        }
        
        Product product = getProductById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        return product;
    }

    /**
     * Search products by name with full validation
     * Only available in 100% coverage scenario
     */
    public List<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Product> products = getAllProducts();
        if (products == null || products.isEmpty()) {
            return new ArrayList<>();
        }
        
        return products.stream()
                .filter(p -> p.getName() != null && 
                           p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    /**
     * Get products within a price range with complete validation
     * Only available in 100% coverage scenario
     */
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            return new ArrayList<>();
        }
        
        List<Product> products = getAllProducts();
        if (products == null || products.isEmpty()) {
            return new ArrayList<>();
        }
        
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .toList();
    }
}