package catsmarketplacetsap.spacecatsmarketplace.mapper;

import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.ProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    ProductDto toDto(ProductEntity productEntity);

    @Mapping(target = "category", ignore = true)
    ProductEntity toEntity(ProductDto productDto);
}