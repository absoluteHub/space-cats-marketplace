package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.dto.CategoryDto;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.service.CategoryService;
import catsmarketplacetsap.spacecatsmarketplace.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@DisplayName("Product Service Integration Test")
class ProductServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("Should add, update and delete a product")
    void testProductLifecycle() {
        CategoryDto category = categoryService.save(new CategoryDto(null, "Equipment", "tools"));

        ProductDto newProduct = new ProductDto(
                null,
                "Space Helmet",
                "High-quality helmet",
                250.5
        );

        ProductDto added = productService.save(newProduct);
        assertThat(added.getId()).isNotNull();
        assertThat(added.getName()).isEqualTo("Space Helmet");

        ProductDto updateData = new ProductDto(
                added.getId(),
                "Updated Helmet",
                "New description",
                300.0
        );

        ProductDto updated = productService.update(added.getId(), updateData);
        assertThat(updated.getName()).isEqualTo("Updated Helmet");
        assertThat(updated.getPrice()).isEqualTo(300.0);

        productService.deleteById(added.getId());

        assertThrows(RuntimeException.class, () -> productService.findById(added.getId()));
    }
}