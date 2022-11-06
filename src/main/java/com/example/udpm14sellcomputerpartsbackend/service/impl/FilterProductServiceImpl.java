package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.daos.FilterProductDao;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.FilterProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class FilterProductServiceImpl implements FilterProductService {

    private final FilterProductDao filterProductDao;
    private final ProductRepository productRepository;

    public FilterProductServiceImpl(
            FilterProductDao filterProductDao,
            ProductRepository productRepository
    ){
        this.filterProductDao = filterProductDao;
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductImageDto> filterProductByPrice(BigDecimal start_price, BigDecimal end_price, Integer page, Integer pageNumber){
       return productRepository.listFilterProduct(start_price,end_price,PageRequest.of(page,pageNumber));
    }

    @Override
    public Page<ProductImageDto> listFilterProductPriceDesc(Integer page, Integer pageNumber){
        return productRepository.listFilterProductPriceDesc(PageRequest.of(page,pageNumber));
    }

    @Override
    public Page<ProductImageDto> listFilterProductPriceAsc(Integer page, Integer pageNumber){
        return productRepository.listFilterProductPriceASC(PageRequest.of(page,pageNumber));
    }


}