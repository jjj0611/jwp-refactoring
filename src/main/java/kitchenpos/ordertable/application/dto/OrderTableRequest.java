package kitchenpos.ordertable.application.dto;

public class OrderTableRequest {
    private int numberOfGuests;
    private boolean empty;

    private OrderTableRequest() {
        this.numberOfGuests = 0;
        this.empty = true;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }
}
