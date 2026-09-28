package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.mapper.ProductMapper;
import catsmarketplacetsap.spacecatsmarketplace.repository.ProductRepository;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.ProductEntity;
import catsmarketplacetsap.spacecatsmarketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        ProductEntity entity = mapper.toEntity(productDto);
        ProductEntity savedEntity = productRepository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setName(productDto.getName());
        existing.setDescription(productDto.getDescription());
        existing.setPrice(productDto.getPrice());

        ProductEntity updated = productRepository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}