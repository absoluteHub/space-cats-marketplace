package catsmarketplacetsap.spacecatsmarketplace.mapper;

import catsmarketplacetsap.spacecatsmarketplace.dto.CategoryDto;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.CategoryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryMapper {

    CategoryDto toDto(CategoryEntity entity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity toEntity(CategoryDto dto);
}