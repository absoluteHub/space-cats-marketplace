package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.dto.CategoryDto;
import catsmarketplacetsap.spacecatsmarketplace.mapper.CategoryMapper;
import catsmarketplacetsap.spacecatsmarketplace.repository.CategoryRepository;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.CategoryEntity;
import catsmarketplacetsap.spacecatsmarketplace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toDto(entity);
    }

    @Override
    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        CategoryEntity entity = categoryMapper.toEntity(categoryDto);
        CategoryEntity savedEntity = categoryRepository.save(entity);
        return categoryMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
