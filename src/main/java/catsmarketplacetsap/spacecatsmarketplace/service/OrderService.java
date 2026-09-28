package catsmarketplacetsap.spacecatsmarketplace.service;

import catsmarketplacetsap.spacecatsmarketplace.dto.OrderDto;
import java.util.List;

public interface OrderService {

    List<OrderDto> findAll();

    OrderDto findById(Long id);

    OrderDto save(OrderDto orderDto);
}