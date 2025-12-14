package catsmarketplacetsap.spacecatsmarketplace.service;

import catsmarketplacetsap.spacecatsmarketplace.annotation.FeatureToggle;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CosmoCatService {

    private final ProductService productService;

    @FeatureToggle("cosmoCats")
    public List<ProductDto> getCosmoCats() {
        return productService.findAll();
    }

    @FeatureToggle("kittyProducts")
    public List<ProductDto> getKittyProducts() {
        return productService.findAll();
    }
}
