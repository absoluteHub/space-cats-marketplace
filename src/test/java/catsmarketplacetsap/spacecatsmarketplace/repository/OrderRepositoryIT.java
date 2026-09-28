package catsmarketplacetsap.spacecatsmarketplace.repository;

import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.OrderEntity;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class OrderRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should save order containing multiple products")
    void shouldSaveOrderWithProducts() {

        ProductEntity p1 = ProductEntity.builder().name("Item 1").price(10.0).build();
        ProductEntity p2 = ProductEntity.builder().name("Item 2").price(20.0).build();

        productRepository.saveAll(List.of(p1, p2));

        OrderEntity order = OrderEntity.builder()
                .createdAt(LocalDateTime.now())
                .products(List.of(p1, p2))
                .build();

        OrderEntity savedOrder = orderRepository.save(order);

        assertThat(savedOrder.getId()).isNotNull();

        Optional<OrderEntity> found = orderRepository.findById(savedOrder.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getProducts()).hasSize(2);
        assertThat(found.get().getProducts())
                .extracting(ProductEntity::getName)
                .contains("Item 1", "Item 2");
    }
}