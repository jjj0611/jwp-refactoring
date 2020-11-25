package kitchenpos.application;

import static kitchenpos.KitchenposTestHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Product;
import kitchenpos.dto.ProductCreateRequestDto;
import kitchenpos.dto.ProductResponseDto;

class ProductServiceTest extends ServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;

    @DisplayName("상품을 등록할 수 있다.")
    @Test
    void create() {
        String name = "고추마요 치킨";
        BigDecimal price = BigDecimal.valueOf(18_000);
        ProductCreateRequestDto productRequest = new ProductCreateRequestDto(name, price);

        ProductResponseDto productResponse = productService.create(productRequest);

        assertAll(
            () -> assertThat(productResponse.getId()).isNotNull(),
            () -> assertThat(productResponse.getName()).isEqualTo(name),
            () -> assertThat(productResponse.getPrice().longValue()).isEqualTo(price.longValue())
        );
    }

    @DisplayName("상품 목록을 조회한다.")
    @Test
    void list() {
        BigDecimal price = BigDecimal.valueOf(18_000);
        Product productRequest = createProduct(null, "고추마요 치킨", price);
        productDao.save(productRequest);

        List<ProductResponseDto> actual = productService.list();

        assertThat(actual).hasSize(1);
    }
}