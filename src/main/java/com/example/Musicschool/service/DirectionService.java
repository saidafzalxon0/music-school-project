package com.example.Musicschool.service;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.ResponseDto;

import java.util.List;

public interface DirectionService  {
    ResponseDto<DirectionDto> post(DirectionDto dto);
    ResponseDto<DirectionDto> patch(DirectionDto dto);
    ResponseDto<DirectionDto> get(Long id);
    ResponseDto<List<DirectionDto>> getByDepartment(Long id);
    ResponseDto<List<DirectionDto>> getAll();
    ResponseDto<DirectionDto> delete(Long id);


}
