package catsmarketplacetsap.spacecatsmarketplace.repository;

import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.CategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class CategoryRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should save and find category by ID")
    void shouldSaveAndFindById() {
        CategoryEntity category = CategoryEntity.builder()
                .name("Exotic Food")
                .cosmicTag("yum")
                .build();

        CategoryEntity saved = categoryRepository.save(category);
        Optional<CategoryEntity> found = categoryRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Exotic Food");
    }
}