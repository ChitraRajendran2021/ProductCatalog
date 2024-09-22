package com.inter.ikea.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductData productData;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productData.getAllProducts().add(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Product> getProducts() {
        return productData.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productData.getAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
