package com.example.layertest.Product;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data // auto generate getter, setter, toString
@NoArgsConstructor // generate constructor with no parameter
@Builder // produce builder
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;

    public ProductDTO(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
