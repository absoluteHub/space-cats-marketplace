package catsmarketplacetsap.spacecatsmarketplace.domain;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

     Long id;
     List<Product> items;
     Double totalPrice;
}
