package com.inter.ikea.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductData {
    private static final Logger logger = LoggerFactory.getLogger(ProductData.class);
    
    private List<Product> products = new ArrayList<>();

    public ProductData() {
        // Initialization of mock data
        products.add(new Product(1L, "Product 1", "Category A", "Description 1", 10.99, "https://th.bing.com/th/id/OIP.dglLSK8YZw2XzAvQOCiAFgHaGy?rs=1&pid=ImgDetMain"));
        products.add(new Product(2L, "Product 2", "Category B", "Description 2", 15.99, "https://th.bing.com/th/id/OIP.dglLSK8YZw2XzAvQOCiAFgHaGy?rs=1&pid=ImgDetMain"));
        
        logger.info("Initialized ProductData with {} products", products.size());
    }

    public List<Product> getAllProducts() {
        return products; // Removed logging from here
    }
}
