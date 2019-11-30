package com.example.layertest.Product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> toProductDTOs(List<Product> product);

    ProductDTO toProductDTO(Product product);
}
