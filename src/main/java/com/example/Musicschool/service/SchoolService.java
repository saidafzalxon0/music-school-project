package com.example.Musicschool.service;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.dto.SubjectDto;
import com.example.Musicschool.projection.SubjectDirectionGetProjection;
import com.example.Musicschool.projection.SubjectGetProjection;

import java.util.List;

public interface SchoolService {
    ResponseDto<SchoolDto> post(SchoolDto dto);
    ResponseDto<SchoolDto> patch(SchoolDto dto);
    ResponseDto<SchoolDto> delete(Long id);
    ResponseDto<List<SchoolDto>> get();
}
