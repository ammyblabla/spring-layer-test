package com.example.layertest.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Date currentTime = new Date();

    List<ProductDTO> toProductDTOs(List<Product> product);

    ProductDTO toProductDTO(Product product);

    @Mapping(target="id", ignore = true)
    Product toProduct(ProductDTO productDTO);

}
