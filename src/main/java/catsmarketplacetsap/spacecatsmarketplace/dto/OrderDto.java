package catsmarketplacetsap.spacecatsmarketplace.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class OrderDto {

    Long id;

    @NotNull(message = "Order date cannot be null")
    LocalDateTime createdAt;

    @NotNull(message = "Order must contain products")
    List<ProductDto> products;
}
