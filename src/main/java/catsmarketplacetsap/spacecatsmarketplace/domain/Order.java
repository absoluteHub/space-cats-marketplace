package catsmarketplacetsap.spacecatsmarketplace.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

     Long id;
     LocalDateTime createdAt;
     List<Product> products;
}
