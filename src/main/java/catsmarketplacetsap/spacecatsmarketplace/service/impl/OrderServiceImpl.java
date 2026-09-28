package catsmarketplacetsap.spacecatsmarketplace.service.impl;

import catsmarketplacetsap.spacecatsmarketplace.dto.OrderDto;
import catsmarketplacetsap.spacecatsmarketplace.mapper.OrderMapper;
import catsmarketplacetsap.spacecatsmarketplace.repository.OrderRepository;
import catsmarketplacetsap.spacecatsmarketplace.repository.entity.OrderEntity;
import catsmarketplacetsap.spacecatsmarketplace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return orderMapper.toDto(entity);
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        OrderEntity entity = orderMapper.toEntity(orderDto);

        // Встановлюємо час створення, якщо він не прийшов
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now());
        }

       OrderEntity savedEntity = orderRepository.save(entity);

        return orderMapper.toDto(savedEntity);
    }
}