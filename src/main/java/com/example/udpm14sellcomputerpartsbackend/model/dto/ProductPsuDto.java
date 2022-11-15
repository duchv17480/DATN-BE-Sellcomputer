package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPsuDto {
    private Long productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String description;
    private String imageLink;
    private Long psuId;
    private Float wattage;
    private String size;
    private Long categoryId;
}
