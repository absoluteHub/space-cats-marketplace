package catsmarketplacetsap.spacecatsmarketplace.repository;

import catsmarketplacetsap.spacecatsmarketplace.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
