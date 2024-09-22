package com.inter.ikea.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductData productData;

    @Autowired
    private FuzzySearch fuzzySearch;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        logger.info("Adding new product: {}", product.getName());
        productData.getAllProducts().add(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Product> getProducts() {
        logger.info("Fetching all products");
        return productData.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("query") String query) {
        return fuzzySearch.searchProducts(query, productData.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        logger.info("Fetching product with id: {}", id);
        return productData.getAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(product -> {
                    logger.info("Product found: {}", product.getName());
                    return ResponseEntity.ok(product);
                })
                .orElseGet(() -> {
                    logger.warn("Product with id {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
