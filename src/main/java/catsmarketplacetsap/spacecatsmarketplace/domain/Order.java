package catsmarketplacetsap.spacecatsmarketplace.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private LocalDateTime createdAt;
    private List<Product> products;
}
