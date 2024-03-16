package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.EmployeeDto;
import com.example.Musicschool.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends CommonMapper<EmployeeDto, Employee> {
}
