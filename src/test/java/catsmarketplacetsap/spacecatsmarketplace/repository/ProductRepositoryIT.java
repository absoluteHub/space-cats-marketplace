package catsmarketplacetsap.spacecatsmarketplace.repository;

import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.CategoryEntity;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should save product with category and find it")
    void shouldSaveProductWithCategory() {
        CategoryEntity category = CategoryEntity.builder()
                .name("Test Category")
                .cosmicTag("test-tag")
                .build();
        categoryRepository.save(category);

        ProductEntity product = ProductEntity.builder()
                .name("Super Nova Milk")
                .description("Tasty")
                .price(55.0)
                .category(category)
                .build();

        ProductEntity savedProduct = productRepository.save(product);

        assertThat(savedProduct.getId()).isNotNull();

        Optional<ProductEntity> found = productRepository.findById(savedProduct.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Super Nova Milk");
        assertThat(found.get().getCategory().getName()).isEqualTo("Test Category");
    }
}