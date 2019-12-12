package com.example.layertest.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    ProductMapper productMapper;

    private Product product = Product.builder()
            .name("P1")
            .description("P1 desc")
            .price(new BigDecimal("1"))
            .build();

    private ProductDTO productDTO = ProductDTO.builder()
            .name("P1")
            .description("P1 desc")
            .price(new BigDecimal("1"))
            .build();

    @Test
    public void should_return_productDTO_list_when_call_toProducts_given_product_list() {
        List<ProductDTO> expectedResult = Arrays.asList(productDTO);
        List<Product> input = Arrays.asList(product);
        assertEquals(expectedResult, ProductMapper.INSTANCE.toProductDTOs(input));
    }

    @Test
    public void should_return_productDTO_when_call_toProductDTO_given_product() {
        assertEquals(productDTO, ProductMapper.INSTANCE.toProductDTO(product));
    }

    @Test
    public void should_return_product_when_call_toProduct_given_productDTO() {
        assertEquals(product, ProductMapper.INSTANCE.toProduct(productDTO));
    }
}
