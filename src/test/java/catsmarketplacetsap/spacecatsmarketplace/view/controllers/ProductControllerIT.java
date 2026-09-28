package catsmarketplacetsap.spacecatsmarketplace.view.controllers;

import catsmarketplacetsap.spacecatsmarketplace.dto.CategoryDto;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.service.CategoryService;
import catsmarketplacetsap.spacecatsmarketplace.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ProductControllerIT extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String API_KEY_HEADER = "X-COSMO-KEY";
    private static final String API_KEY_VALUE = "meow-secret-key-123";

    @Test
    @DisplayName("GET /products")
    void shouldReturnAllProducts() throws Exception {
        CategoryDto category = categoryService.save(new CategoryDto(null, "Food", "yummy"));
        ProductDto product = new ProductDto(null, "Space Tuna", "Fresh", 50.0);
        productService.save(product);

        mockMvc.perform(get("/products")
                        .header(API_KEY_HEADER, API_KEY_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Space Tuna")));
    }

    @Test
    @DisplayName("POST /products")
    void shouldCreateProduct() throws Exception {
        ProductDto newProduct = new ProductDto(null, "Moon Boots", "Jump high", 150.0);
        String jsonRequest = objectMapper.writeValueAsString(newProduct);

        mockMvc.perform(post("/products")
                        .header(API_KEY_HEADER, API_KEY_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Moon Boots")))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("DELETE /products/{id}")
    void shouldDeleteProduct() throws Exception {
        ProductDto saved = productService.save(new ProductDto(null, "Old Rocket", "Rusty", 10.0));

        mockMvc.perform(delete("/products/" + saved.getId())
                        .header(API_KEY_HEADER, API_KEY_VALUE))
                .andExpect(status().isNoContent());
    }
}