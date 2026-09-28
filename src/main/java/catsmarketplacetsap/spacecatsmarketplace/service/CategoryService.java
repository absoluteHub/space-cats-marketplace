package catsmarketplacetsap.spacecatsmarketplace.service;

import catsmarketplacetsap.spacecatsmarketplace.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    void deleteById(Long id);
}