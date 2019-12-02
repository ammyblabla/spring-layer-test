package com.example.layertest.Product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    // provide alternative entity
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProductRepository productRepository;

    // mock database ?
    @Before
    public void setup() {
        // given
        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();
        testEntityManager.persist(product);
    }

    @Test
    public void should_return_id_when_find_by_id() {
        //given
        Long id = 1L;
        Product expectedResult = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();
        expectedResult.setId(1L);

        //when
        Optional<Product> actualResult = productRepository.findById(id);

        //then
        assertEquals(id, actualResult.get().getId());
        assertEquals(expectedResult.getDescription(), actualResult.get().getDescription());
        assertEquals(expectedResult.getName(), actualResult.get().getName());
        assertEquals(expectedResult.getPrice(), actualResult.get().getPrice());
    }


    @Test
    public void when_find_all_the_return_product_list() {
        // when
        List<Product> actualResult = productRepository.findAll();
        // then
        assertEquals(actualResult.size(),1);
    }

    @Test
    public void should_save_product_when_save_product() {
        //given
        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        // when
        Product actualResult = productRepository.save(product);

        // then
        Assert.assertNotNull(actualResult.getId());
        assertEquals(product.getDescription(), actualResult.getDescription());
        assertEquals(product.getName(), actualResult.getName());
        assertEquals(product.getPrice(), actualResult.getPrice());

    }
}