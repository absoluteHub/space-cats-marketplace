package catsmarketplacetsap.spacecatsmarketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CategoryDto {

    Long id;

    @NotBlank
    @Size(min = 2, max = 30, message = "Category name must be 2–30 characters")
    String name;

    @Size(max = 30, message = "Cosmic tag must be up to 30 characters")
    String cosmicTag;
}
