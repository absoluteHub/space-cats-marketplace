package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.dto.CategoryDto;
import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DisplayName("Category Service Integration Test")
@WithMockUser(username = "admin", roles = {"ADMIN"})
class CategoryServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("Should create and return category DTO with ID")
    void shouldCreateCategory() {
        CategoryDto newCategory = new CategoryDto(null, "Spaceships", "fast");

        CategoryDto created = categoryService.save(newCategory);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Spaceships");
    }

    @Test
    @DisplayName("Should return all categories")
    void shouldFindAll() {
        categoryService.save(new CategoryDto(null, "Cat1", "tag1"));
        categoryService.save(new CategoryDto(null, "Cat2", "tag2"));

        List<CategoryDto> all = categoryService.findAll();

        assertThat(all).hasSizeGreaterThanOrEqualTo(2);
    }
}