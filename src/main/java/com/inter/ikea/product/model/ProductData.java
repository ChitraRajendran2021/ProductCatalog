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
        products.add(new Product(1L, "Mattress", "Bed", "Sprung base incl. mattress pad, 120x200 cm",5500 , "https://www.ikea.com/se/en/images/products/skarer-sprung-base-incl-mattress-pad-medium-firm-tuddal__0858267_pe562570_s5.jpg?f=xxs"));
        products.add(new Product(2L, "Sofa", "Sofa", "3 Seater Sofa", 4500, "https://www.ikea.com/se/en/images/products/soederhamn-3-seat-section-gunnared-beige__0767189_pe754050_s5.jpg?f=xxs"));
        products.add(new Product(3L, "Tables", "Kitchen Table", "Kitchen extendable table 4 leg teak wood",6500 , "https://www.ikea.com/se/en/images/products/skansnaes-extendable-table-brown-beech-veneer__1322525_pe942201_s5.jpg?f=xxs"));
        products.add(new Product(4L, "Sofa", "Arm Chair", "Arm Chair brown color", 2500, "https://www.ikea.com/se/en/images/products/ekenaeset-armchair-kilanda-light-beige__1179060_pe895831_s5.jpg?f=xxs"));
        
        logger.info("Initialized ProductData with {} products", products.size());
    }

    public List<Product> getAllProducts() {
        return products; // Removed logging from here
    }
}
