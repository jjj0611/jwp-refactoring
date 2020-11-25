package kitchenpos.application;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.dao.OrderTableDao;
import kitchenpos.dao.TableGroupDao;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderStatus;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.TableGroup;
import kitchenpos.dto.OrderTableResponseDto;
import kitchenpos.repository.OrderRepository;

class TableServiceTest extends ServiceTest {
    @Autowired
    private TableService tableService;
    @Autowired
    private OrderTableDao orderTableDao;
    @Autowired
    private TableGroupDao tableGroupDao;
    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void create() {
        OrderTableResponseDto orderTableResponse = tableService.create();

        assertThat(orderTableResponse.getId()).isNotNull();
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void list() {
        TableGroup tableGroup = tableGroupDao.save(new TableGroup());
        orderTableDao.save(new OrderTable(null, tableGroup.getId(), 2, false));

        List<OrderTableResponseDto> orderTables = tableService.list();

        assertThat(orderTables).hasSize(1);
    }

    @DisplayName("빈 테이블로 변경할 수 있다.")
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void changeEmpty(boolean status) {
        OrderTableResponseDto orderTableResponse = tableService.create();

        OrderTableResponseDto changedOrderTable = tableService.changeEmpty(orderTableResponse.getId(), status);

        assertThat(changedOrderTable.isEmpty()).isEqualTo(status);
    }

    @DisplayName("빈 테이블로 변경 시, 주문 상태가 조리 또는 식사인 주문 테이블은 빈 테이블로 설정할 수 없다.")
    @ParameterizedTest
    @CsvSource({"COOKING", "MEAL"})
    void changeEmpty_WithInvalidOrderState_ThrownException(OrderStatus status) {
        OrderTable table = orderTableDao.save(new OrderTable(null, null, 0, false));
        orderRepository.save(new Order(null, table.getId(), status, LocalDateTime.now()));

        assertThatThrownBy(() -> tableService.changeEmpty(table.getId(), true))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 테이블의 손님 수를 변경할 수 있다.")
    @Test
    void changeNumberOfGuests() {
        OrderTable orderTable = orderTableDao.save(new OrderTable(null, null, 1, false));

        OrderTableResponseDto changedOrderTable = tableService.changeNumberOfGuests(orderTable.getId(), 3);

        assertThat(changedOrderTable.getNumberOfGuests()).isEqualTo(3);
    }
}