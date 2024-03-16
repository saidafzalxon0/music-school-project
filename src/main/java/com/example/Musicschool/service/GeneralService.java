package com.example.Musicschool.service;

import com.example.Musicschool.dto.GeneralDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;

import java.util.List;

public interface GeneralService {
    ResponseDto<GeneralDto> post(GeneralDto dto);
    ResponseDto<GeneralDto> patch(GeneralDto dto);
    ResponseDto<GeneralDto> delete(Long id);
    ResponseDto<List<GeneralDto>> get();
}
