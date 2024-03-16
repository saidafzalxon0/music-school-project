package com.example.Musicschool.service;

import com.example.Musicschool.dto.DepartmentDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.projection.DepartmentProjection;

import java.util.List;

public interface DepartmentService {
    ResponseDto<DepartmentDto> post(DepartmentDto dto);
    ResponseDto<DepartmentDto> patch(DepartmentDto dto);

    ResponseDto<DepartmentDto> delete(Long id);

    ResponseDto<List<DepartmentProjection>> getAllDirection(Long id);
    ResponseDto<List<DepartmentProjection>> getAllDepartment();

    ResponseDto<List<DepartmentDto>> getAll();
}
