package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChipService {

    List<ChipDto> findAll();

    List<ChipDto> findAllByProduct(Long id);

    ChipDto findById(Long id);

    ChipDto create(ChipDto chipDto);

    ChipDto update(Long id, ChipDto chipDto);

    void delete(Long id);
}
