package kitchenpos.order.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import kitchenpos.order.model.Order;
import kitchenpos.order.model.OrderStatus;
import kitchenpos.orderline.application.dto.OrderLineRequestDto;

public class OrderRequestDto {
    private Long orderTableId;
    private List<OrderLineRequestDto> orderLineCreateRequests;

    private OrderRequestDto() {
    }

    public OrderRequestDto(Long orderTableId,
        List<OrderLineRequestDto> orderLineCreateRequests) {
        this.orderTableId = orderTableId;
        this.orderLineCreateRequests = orderLineCreateRequests;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public List<OrderLineRequestDto> getOrderLineCreateRequests() {
        return orderLineCreateRequests;
    }

    public Order toEntity() {
        return new Order(null, orderTableId, OrderStatus.COOKING, LocalDateTime.now());
    }
}
