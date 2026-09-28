package catsmarketplacetsap.spacecatsmarketplace.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

     Long id;
     String name;
     String cosmicTag;
}