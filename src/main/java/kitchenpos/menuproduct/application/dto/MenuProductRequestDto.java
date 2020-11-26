package kitchenpos.menuproduct.application.dto;

import kitchenpos.menuproduct.model.MenuProduct;

public class MenuProductRequestDto {
    private Long productId;
    private long quantity;

    private MenuProductRequestDto() {
    }

    public MenuProductRequestDto(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public MenuProduct toEntity(Long menuId) {
        return new MenuProduct(null, menuId, productId, quantity);
    }

    public MenuProduct toEntity() {
        return new MenuProduct(null, null, productId, quantity);
    }
}
