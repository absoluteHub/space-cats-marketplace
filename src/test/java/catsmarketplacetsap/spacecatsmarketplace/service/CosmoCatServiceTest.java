package catsmarketplacetsap.spacecatsmarketplace.service;

import catsmarketplacetsap.spacecatsmarketplace.aspect.FeatureToggleAspect;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.service.exception.FeatureNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CosmoCatServiceTest {

    private CosmoCatService proxiedService;

    @Mock
    private ProductService productService;

    @Mock
    private FeatureToggleService featureToggleService;

    @BeforeEach
    void setUp() {
        CosmoCatService targetService = new CosmoCatService(productService);

        FeatureToggleAspect aspect = new FeatureToggleAspect(featureToggleService);

        AspectJProxyFactory factory = new AspectJProxyFactory(targetService);
        factory.addAspect(aspect);

        proxiedService = factory.getProxy();
    }

    @Test
    @DisplayName("Should return products when feature 'cosmoCats' is ENABLED")
    void shouldReturnProducts_WhenCosmoCatsEnabled() {
        String featureName = "cosmoCats";
        when(featureToggleService.check(featureName)).thenReturn(true);

        ProductDto mockProduct = new ProductDto(1L, "Space Star", "Desc", 100.0);
        when(productService.findAll()).thenReturn(List.of(mockProduct));

        List<ProductDto> result = proxiedService.getCosmoCats();

        verify(productService, times(1)).findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Should throw exception when feature 'cosmoCats' is DISABLED")
    void shouldThrowException_WhenCosmoCatsDisabled() {
        String featureName = "cosmoCats";
        when(featureToggleService.check(featureName)).thenReturn(false);

        FeatureNotAvailableException exception = assertThrows(FeatureNotAvailableException.class, () -> {
            proxiedService.getCosmoCats();
        });

        assertTrue(exception.getMessage().contains("disabled"));

        verify(productService, never()).findAll();
    }

    @Test
    @DisplayName("Should throw exception when feature 'kittyProducts' is DISABLED")
    void shouldThrowException_WhenKittyProductsDisabled() {
        String featureName = "kittyProducts";
        when(featureToggleService.check(featureName)).thenReturn(false);

        assertThrows(FeatureNotAvailableException.class, () -> {
            proxiedService.getKittyProducts();
        });

        verify(productService, never()).findAll();
    }
}
