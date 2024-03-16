package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.GovernmentDto;
import com.example.Musicschool.entity.Government;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GovernmentMapper extends CommonMapper<GovernmentDto, Government> {
}
