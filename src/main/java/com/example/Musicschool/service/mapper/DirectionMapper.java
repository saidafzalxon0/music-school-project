package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.entity.Direction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionMapper extends CommonMapper<DirectionDto, Direction> {
}
