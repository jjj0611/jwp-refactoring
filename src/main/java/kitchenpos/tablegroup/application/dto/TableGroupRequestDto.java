package kitchenpos.tablegroup.application.dto;

import java.util.List;

public class TableGroupRequestDto {
    private List<Long> orderTableIds;

    private TableGroupRequestDto() {
    }

    public TableGroupRequestDto(List<Long> orderTableIds) {
        this.orderTableIds = orderTableIds;
    }

    public List<Long> getOrderTableIds() {
        return orderTableIds;
    }
}
