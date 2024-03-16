package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.entity.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends CommonMapper<FileDto, File> {
}
