package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.AcceptanceDto;
import com.example.Musicschool.entity.Acceptance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcceptanceMapper extends CommonMapper<AcceptanceDto, Acceptance> {
}
