package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.DepartmentDto;
import com.example.Musicschool.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends CommonMapper<DepartmentDto, Department> {
}
