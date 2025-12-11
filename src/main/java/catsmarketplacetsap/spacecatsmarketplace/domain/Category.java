package catsmarketplacetsap.spacecatsmarketplace.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String cosmicTag;
}