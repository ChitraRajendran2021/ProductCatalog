package com.inter.ikea.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductData productData;

    @InjectMocks
    private ProductController productController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddProduct() throws Exception {
        // Create a product to add
        Product product = new Product(3L, "Tables", "Kitchen Table", "Kitchen extendable table 4 leg teak wood", 6500, "https://www.ikea.com/se/en/images/products/skansnaes-extendable-table-brown-beech-veneer__1322525_pe942201_s5.jpg?f=xxs");

        // Mock the product list
        List<Product> products = new ArrayList<>();
        when(productData.getAllProducts()).thenReturn(products);

        // Perform the POST request to add a product
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        // Capture the added product
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productData, times(1)).getAllProducts();
        products.add(product); // Add product to the mocked list
    }

    @Test
    public void testGetProducts() throws Exception {
        // Create some sample products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Mattress", "Bed", "Sprung base incl. mattress pad, 120x200 cm", 5500, "https://www.ikea.com/se/en/images/products/skarer-sprung-base-incl-mattress-pad-medium-firm-tuddal__0858267_pe562570_s5.jpg?f=xxs"));
        products.add(new Product(2L, "Sofa", "Sofa", "3 Seater Sofa", 4500, "https://www.ikea.com/se/en/images/products/soederhamn-3-seat-section-gunnared-beige__0767189_pe754050_s5.jpg?f=xxs"));
        products.add(new Product(4L, "Sofa", "Arm Chair", "Arm Chair brown color", 2500, "https://www.ikea.com/se/en/images/products/ekenaeset-armchair-kilanda-light-beige__1179060_pe895831_s5.jpg?f=xxs"));

        // Mock the productData to return the list of products
        when(productData.getAllProducts()).thenReturn(products);

        // Perform GET request and verify the result
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Mattress"))
                .andExpect(jsonPath("$[1].name").value("Sofa"));
    }

    @Test
    public void testGetProduct() throws Exception {
        // Create a product
        Product product = new Product(1L, "Mattress", "Bed", "Sprung base incl. mattress pad, 120x200 cm", 5500, "https://www.ikea.com/se/en/images/products/skarer-sprung-base-incl-mattress-pad-medium-firm-tuddal__0858267_pe562570_s5.jpg?f=xxs");

        // Mock the productData to return the product
        when(productData.getAllProducts()).thenReturn(List.of(product));

        // Perform GET request for a specific product and verify the result
        mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Mattress"));
    }

    @Test
    public void testGetProductNotFound() throws Exception {
        // Mock an empty list for no product found
        when(productData.getAllProducts()).thenReturn(new ArrayList<>());

        // Perform GET request for a non-existing product ID and expect 404
        mockMvc.perform(get("/products/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
