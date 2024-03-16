package com.example.Musicschool.service;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.dto.StatisticDto;

import java.util.List;

public interface StatisticService {
    ResponseDto<StatisticDto> post(StatisticDto dto);
    ResponseDto<StatisticDto> patch(StatisticDto dto);
    ResponseDto<StatisticDto> delete(Long id);
    ResponseDto<List<StatisticDto>> get();
}
