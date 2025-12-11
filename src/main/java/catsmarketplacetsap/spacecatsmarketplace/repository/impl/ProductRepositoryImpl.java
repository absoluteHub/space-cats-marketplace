package catsmarketplacetsap.spacecatsmarketplace.repository.impl;

import catsmarketplacetsap.spacecatsmarketplace.domain.Product;
import catsmarketplacetsap.spacecatsmarketplace.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong counterId = new AtomicLong(0);

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            long id = counterId.incrementAndGet();
            product.setId(id);
        }
        products.put(product.getId(), product);
    }
}
