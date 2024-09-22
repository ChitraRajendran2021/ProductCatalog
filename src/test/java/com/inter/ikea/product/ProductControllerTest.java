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
        Product product = new Product(3L, "Product 3", "Category C", "Description 3", 20.99, "imageUrl");

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        // To Verify that the product was added to the productData
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productData, times(1)).getAllProducts();
        productData.getAllProducts().add(product);
    }

    @Test
    public void testGetProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", "Category A", "Description 1", 10.99, "imageUrl"));
        products.add(new Product(2L, "Product 2", "Category B", "Description 2", 15.99, "imageUrl"));

        when(productData.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product(1L, "Product 1", "Category A", "Description 1", 10.99, "imageUrl");
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productData.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    public void testGetProductNotFound() throws Exception {
        when(productData.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
