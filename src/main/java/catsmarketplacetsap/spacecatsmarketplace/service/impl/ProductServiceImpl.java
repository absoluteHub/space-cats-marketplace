package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.domain.Product;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.mapper.ProductMapper;
import catsmarketplacetsap.spacecatsmarketplace.repository.ProductRepository;
import catsmarketplacetsap.spacecatsmarketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapper.toDto(product);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = mapper.toEntity(productDto);
        productRepository.save(product);
        return mapper.toDto(product);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Product updated = mapper.toEntity(productDto);
        updated.setId(id);

        productRepository.save(updated);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}

