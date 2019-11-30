package com.example.layertest.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j  // Causes lombok to generate a logger field.
@RequiredArgsConstructor

@RestController
@RequestMapping("/products")
public class ProductAPI {
    @Autowired
    private final ProductService productService;
//    @Autowired
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOS = productMapper.toProductDTOs(products);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("P1");
        System.out.println(productDTO);
        return ResponseEntity.ok(productDTO);
    }
}