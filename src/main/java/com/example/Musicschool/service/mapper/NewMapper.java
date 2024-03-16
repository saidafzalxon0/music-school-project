package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.NewDto;
import com.example.Musicschool.entity.New;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewMapper extends CommonMapper<NewDto, New> {
}
