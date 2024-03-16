package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.SubjectDto;
import com.example.Musicschool.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends CommonMapper<SubjectDto, Subject> {
}
