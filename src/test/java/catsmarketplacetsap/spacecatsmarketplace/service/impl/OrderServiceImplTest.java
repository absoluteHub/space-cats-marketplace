package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.dto.OrderDto;
import catsmarketplacetsap.spacecatsmarketplace.dto.ProductDto;
import catsmarketplacetsap.spacecatsmarketplace.integration.AbstractIntegrationTest;
import catsmarketplacetsap.spacecatsmarketplace.service.OrderService;
import catsmarketplacetsap.spacecatsmarketplace.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DisplayName("Order Service Integration Test")
@WithMockUser(username = "admin", roles = {"ADMIN"})
class OrderServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("Should create order with products")
    void shouldCreateOrder() {
        ProductDto p1 = productService.save(new ProductDto(null, "Hyperdrive", "Fast", 1000.0));
        ProductDto p2 = productService.save(new ProductDto(null, "Coolant", "Cold", 50.0));

        OrderDto orderDto = new OrderDto(
                null,
                LocalDateTime.now(),
                List.of(p1, p2)
        );

        OrderDto savedOrder = orderService.save(orderDto);

        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getCreatedAt()).isNotNull();
        assertThat(savedOrder.getProducts()).hasSize(2);

        OrderDto foundOrder = orderService.findById(savedOrder.getId());
        assertThat(foundOrder.getProducts()).extracting(ProductDto::getName)
                .contains("Hyperdrive", "Coolant");
    }
}
