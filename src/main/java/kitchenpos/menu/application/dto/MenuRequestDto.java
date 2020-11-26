package kitchenpos.menu.application.dto;

import java.math.BigDecimal;
import java.util.List;

import kitchenpos.menu.model.Menu;
import kitchenpos.menuproduct.application.dto.MenuProductRequestDto;

public class MenuRequestDto {
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductRequestDto> menuProductRequestDto;

    private MenuRequestDto() {
    }

    public MenuRequestDto(String name, BigDecimal price, Long menuGroupId,
        List<MenuProductRequestDto> menuProductRequestDto) {
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProductRequestDto = menuProductRequestDto;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductRequestDto> getMenuProductRequestDto() {
        return menuProductRequestDto;
    }

    public Menu toEntity() {
        return new Menu(null, name, price, menuGroupId);
    }
}
