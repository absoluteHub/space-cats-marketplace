package catsmarketplacetsap.spacecatsmarketplace.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;

import java.util.List;

@Value
public class CartDto {

    Long id;

    @NotNull(message = "Cart items cannot be null")
    List<ProductDto> items;

    @PositiveOrZero(message = "Total price must be >= 0")
    Double totalPrice;
}
