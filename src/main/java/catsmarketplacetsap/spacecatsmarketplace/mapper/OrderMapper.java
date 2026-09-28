package catsmarketplacetsap.spacecatsmarketplace.mapper;

import catsmarketplacetsap.spacecatsmarketplace.dto.OrderDto;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.OrderEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, builder = @Builder(disableBuilder = true))
public interface OrderMapper {

    OrderDto toDto(OrderEntity entity);

    OrderEntity toEntity(OrderDto dto);
}