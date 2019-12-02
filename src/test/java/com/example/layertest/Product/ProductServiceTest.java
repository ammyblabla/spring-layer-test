package com.example.layertest.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void when_find_all_return_product_list() {
        // given
        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        List<Product> expectedResult = Arrays.asList(product);
        doReturn(expectedResult).when(productRepository).findAll();

        // when
        List<Product> actualResult = productService.findAll();

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_return_product_when_find_by_id() throws Exception{
        // given
        Optional<Product> expectedProduct = Optional.of(Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build());

        doReturn(expectedProduct).when(productRepository).findById(1L);

        // when
        Optional<Product> actualResult = productService.findById(1L);

        // then
        assertThat(actualResult).isEqualTo(expectedProduct);

    }

    @Test
    public void should_return_product_when_add_product() {
        //given
        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        doReturn(product).when(productRepository).save(product);

        // when
        productService.save(product);

        // then
        verify(productRepository, times(1)).save(product);
    }
}
