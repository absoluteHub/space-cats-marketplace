package catsmarketplacetsap.spacecatsmarketplace.mapper;

import catsmarketplacetsap.spacecatsmarketplace.domain.Product;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
