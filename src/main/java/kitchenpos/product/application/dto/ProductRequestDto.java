package kitchenpos.product.application.dto;

import java.math.BigDecimal;

import kitchenpos.product.model.Product;

public class ProductRequestDto {
    private String name;
    private BigDecimal price;

    private ProductRequestDto() {
    }

    public ProductRequestDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product toEntity() {
        return new Product(null, this.name, this.price);
    }
}
