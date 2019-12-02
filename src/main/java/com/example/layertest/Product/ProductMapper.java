package com.example.layertest.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> toProductDTOs(List<Product> product);

    ProductDTO toProductDTO(Product product);

    @Mapping(target="createAt", ignore = true)
    @Mapping(target="updateAt", ignore = true)
    @Mapping(target="id", ignore = true)
    Product toProduct(ProductDTO productDTO);
}
