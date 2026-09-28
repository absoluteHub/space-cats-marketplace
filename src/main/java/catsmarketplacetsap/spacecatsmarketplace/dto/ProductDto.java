package catsmarketplacetsap.spacecatsmarketplace.dto;

import catsmarketplacetsap.spacecatsmarketplace.validation.CosmicWordCheck;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class ProductDto {

    Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @CosmicWordCheck(message = "Product name must include cosmic terms like star, galaxy or comet")
    String name;

    @NotBlank
    @Size(min = 2, max = 500)
    String description;

    @NotNull
    @Min(0)
    @Max(1000)
    Double price;
}
