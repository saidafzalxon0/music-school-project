package com.example.Musicschool.service;

import com.example.Musicschool.dto.EmployeeDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.projection.EmployeeIdProjection;
import com.example.Musicschool.projection.EmployeeLevelProjection;
import com.example.Musicschool.projection.EmployeeProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    ResponseDto<EmployeeIdProjection> post(String fullName, String about, String editor, Long position_id, Long direction_id, Integer type,Integer level, MultipartFile photo_file, List<MultipartFile> list_files);
    ResponseDto<EmployeeIdProjection> patch(Long id,String fullName, String about, String editor, Long position_id, Long direction_id, Integer type,Integer level, MultipartFile photo_file, List<MultipartFile> list_files,Integer list_id);
    ResponseDto<EmployeeIdProjection> getById(Long id);
    ResponseDto<List<EmployeeIdProjection>> getByType(Integer id);
    ResponseDto<List<EmployeeLevelProjection>> getByDirection(Long id);
    ResponseDto<List<EmployeeProjection>> getAll();
    ResponseDto<EmployeeIdProjection> delete(Long id);
}
