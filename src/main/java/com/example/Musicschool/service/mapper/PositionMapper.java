package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.PositionDto;
import com.example.Musicschool.entity.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper extends CommonMapper<PositionDto, Position> {
}
