package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    List<CategoryEntity> findAllByStatusEquals(StatusEnum status);

    Page<CategoryEntity> findByStatusEquals(StatusEnum status, Pageable pageable);


}