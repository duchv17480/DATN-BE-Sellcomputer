package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface FilterProductService {
    Page<ProductImageDto> filterProductByPrice(BigDecimal start_price, BigDecimal end_price,Integer page, Integer pageNumber);

    Page<ProductImageDto> listFilterProductPriceDesc(Integer page, Integer pageNumber);

    Page<ProductImageDto> listFilterProductPriceAsc(Integer page, Integer pageNumber);
}
