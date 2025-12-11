package catsmarketplacetsap.spacecatsmarketplace.domain;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Long id;
    private List<Product> items;
    private Double totalPrice;
}
