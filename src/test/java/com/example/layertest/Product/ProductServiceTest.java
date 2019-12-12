package com.example.layertest.Product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    Product product = Product.builder()
            .name("P1")
            .description("P1 desc")
            .price(new BigDecimal("1"))
            .build();

    Long id = 1L;

    @Test
    public void when_find_all_return_product_list() {
        // given
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
        Optional<Product> expectedProduct = Optional.of(product);

        doReturn(expectedProduct).when(productRepository).findById(id);

        // when
        Optional<Product> actualResult = productService.findById(id);

        // then
        assertThat(actualResult).isEqualTo(expectedProduct);

    }

    @Test
    public void should_return_product_when_add_product() {
        doReturn(product).when(productRepository).save(product);

        // when
        Product actualProduct = productService.save(product);

        // then
        verify(productRepository, times(1)).save(product);
        assertThat(actualProduct.getName()).isEqualTo(product.getName());
        assertThat(actualProduct.getName()).isEqualTo(product.getName());
        assertThat(actualProduct.getPrice()).isEqualTo(product.getPrice());

    }

    @Test
    public void should_update_product_when_put_product() {
        Product givenProduct  = Product.builder()
                .name("P2")
                .description("P2 desc")
                .price(new BigDecimal("200"))
                .build();

        doReturn(givenProduct).when(productRepository).save(givenProduct);

        Product actualProduct = productService.updateProduct(id,givenProduct);

        assertThat(actualProduct.getId()).isEqualTo(id);
        assertThat(actualProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(actualProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(actualProduct.getPrice()).isEqualTo(givenProduct.getPrice());

    }
}
