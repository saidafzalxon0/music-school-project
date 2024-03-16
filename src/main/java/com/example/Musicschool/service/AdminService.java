package com.example.Musicschool.service;

import com.example.Musicschool.dto.AdminDto;
import com.example.Musicschool.dto.DepartmentDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.projection.DepartmentProjection;

import java.util.List;

public interface AdminService {
    ResponseDto<AdminDto> signUp(AdminDto dto);
    ResponseDto<String> signIn(AdminDto dto);
    ResponseDto<String> patch(AdminDto dto);

    ResponseDto<AdminDto> delete(Long id);

    ResponseDto<List<AdminDto>> getAllAdmin();
}
