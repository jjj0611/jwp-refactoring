package kitchenpos.menugroup.application.dto;

import kitchenpos.menugroup.model.MenuGroup;

public class MenuGroupRequestDto {
    private String name;

    private MenuGroupRequestDto() {
    }

    public MenuGroupRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MenuGroup toEntity() {
        return new MenuGroup(null, name);
    }
}
