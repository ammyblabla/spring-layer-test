package com.example.layertest.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    private ProductDTO productDTO = ProductDTO.builder()
        .name("P1")
        .description("P1 desc")
        .price(new BigDecimal("1"))
        .build();

    private Product product = Product.builder()
        .name("P1")
        .description("P1 desc")
        .price(new BigDecimal("1"))
        .build();

    private Long id = 1L;

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

        doReturn(productDTO).when(productMapper).toProductDTO(product);
        doReturn(Optional.of(product)).when(productService).findById(id);

        // when
        this.mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productDTO.getName())));
    }

    @Test
    public void should_save_product_when_call_post_api() throws Exception {
        // given
        doReturn(this.product).when(productService).save(product);
        doReturn(this.product).when(productMapper).toProduct(productDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/products")
                    .content(new ObjectMapper().writeValueAsString(product))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    @Test
    public void should_update_product_when_update_given_id() throws Exception {
        Product newProduct = Product.builder()
                .name("new product")
                .description("new product desc")
                .price(new BigDecimal("1000"))
                .build();

        ProductDTO newProductDTO = ProductDTO.builder()
                .name("new product")
                .description("new product desc")
                .price(new BigDecimal("1000"))
                .build();


        doReturn(newProduct).when(productService).updateProduct(id,newProduct);
        doReturn(newProduct).when(productMapper).toProduct(newProductDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                .put("/products/1")
                .content(new ObjectMapper().writeValueAsString(newProduct))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

    }
}