package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeThangVaNamDto;

import java.util.List;

public interface StatisticalService {
    List<StatisticalDto> listHoaDonCacNam();

    List<ThongKeThangVaNamDto> listHoaDonCacThangVaNam();

    List<StatisticalDto> listThongKeTheoNam(Integer year);

    List<ThongKeDto> listThongKeTheoThangVaNam(Integer year, Integer month);
}
