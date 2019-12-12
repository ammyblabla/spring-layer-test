package com.example.layertest.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j  // Causes lombok to generate a logger field.
@RequiredArgsConstructor

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOS = productMapper.toProductDTOs(products);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        ProductDTO productDTO = productMapper.toProductDTO(product.get());
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<Product> saveOne(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        System.out.println(product);
        return new ResponseEntity<Product>(productService.updateProduct(id, product), HttpStatus.OK);
    }
}
