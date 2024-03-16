package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.GeneralDto;
import com.example.Musicschool.entity.General;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralMapper  extends CommonMapper<GeneralDto, General>{
}
