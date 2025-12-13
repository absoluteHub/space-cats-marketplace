package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.domain.Product;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.mapper.ProductMapper;
import catsmarketplacetsap.spacecatsmarketplace.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);

        productDto = new ProductDto(
                1L,
                "Cosmic star product",
                "Test description",
                100.0
        );
    }

    @Test
    @DisplayName("findAll should return list of ProductDto")
    void findAll_shouldReturnDtos() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(mapper.toDto(product)).thenReturn(productDto);

        List<ProductDto> result = productService.findAll();

        assertEquals(1, result.size());
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("findById should return product dto")
    void findById_shouldReturnDto() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(mapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.findById(1L);

        assertNotNull(result);
        verify(productRepository).findById(1L);
    }

    @Test
    @DisplayName("findById should throw when product not found")
    void findById_shouldThrow() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> productService.findById(1L));
    }

    @Test
    @DisplayName("save should persist product")
    void save_shouldPersist() {
        when(mapper.toEntity(productDto)).thenReturn(product);
        when(mapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.save(productDto);

        assertNotNull(result);
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("deleteById should delete when exists")
    void deleteById_shouldDelete() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteById(1L);

        verify(productRepository).deleteById(1L);
    }
}

