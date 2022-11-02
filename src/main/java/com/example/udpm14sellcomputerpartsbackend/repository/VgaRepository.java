package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.VgaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VgaRepository extends JpaRepository<VgaEntity, Long> {
    List<VgaEntity> findByProductId(Long id);

}
