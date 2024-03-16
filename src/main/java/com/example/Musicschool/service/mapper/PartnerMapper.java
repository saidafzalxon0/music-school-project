package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.entity.Partner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartnerMapper extends CommonMapper<PartnerDto, Partner>  {
}
