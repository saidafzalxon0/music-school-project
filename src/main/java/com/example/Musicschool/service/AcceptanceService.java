package com.example.Musicschool.service;

import com.example.Musicschool.dto.AcceptanceDto;
import com.example.Musicschool.dto.ResponseDto;

import java.util.List;

public interface AcceptanceService {
    ResponseDto<AcceptanceDto> post(AcceptanceDto dto);
    ResponseDto<AcceptanceDto> patch(AcceptanceDto dto);
    ResponseDto<AcceptanceDto> delete(Long id);
    ResponseDto<List<AcceptanceDto>> get();
}
