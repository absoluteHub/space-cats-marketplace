package catsmarketplacetsap.spacecatsmarketplace.repository;

import catsmarketplacetsap.spacecatsmarketplace.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    void save(Product product);
}
