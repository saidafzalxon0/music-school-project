package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.entity.School;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper extends CommonMapper<SchoolDto,School> {
}
