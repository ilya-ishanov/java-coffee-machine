package kz.solvatech.testtask.mapper;

import kz.solvatech.testtask.dto.OrderDto;
import kz.solvatech.testtask.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);
    OrderDto toDto(Order order);
}
