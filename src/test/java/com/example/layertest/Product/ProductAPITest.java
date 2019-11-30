package com.example.layertest.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    public void when_find_all_then_return_product_dto_list() throws Exception {
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        List<ProductDTO> productDTOS = Arrays.asList(productDTO);

        doReturn(new ArrayList<>()).when(productService).findAll();
        doReturn(productDTOS).when(productMapper).toProductDTOs(any());

        //when + then
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(productDTO.getName())));
    }

    @Test
    public void should_return_productDTO_when_find_by_id_given_id() throws Exception {
        // given
        ProductDTO productDTO = ProductDTO.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        Long id = 1L;

        doReturn(productDTO).when(productMapper).toProductDTO(product);
        doReturn(product).when(productService).findById(id);

        // when
        this.mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productDTO.getName())));
    }

}
